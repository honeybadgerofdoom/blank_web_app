package com.tco.requests;

import com.tco.misc.UnauthorizedRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.database.Database;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class LoginRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(LoginRequest.class);
    private String username;
    private String password;
    private int userID = 0;

    @Override
    public void buildResponse() throws UnauthorizedRequestException {
        login(this.username, this.password);
        log.trace("buildResponse -> {}", this);
    }

    public boolean login(String username, String password) throws UnauthorizedRequestException {
        try (Database connection = new Database()) {

            String salt = fetchSalt(connection, username);
            String saltedPassword = sha256(password + salt);
            log.info("Salt: " + salt);
            log.info("Salted: " + saltedPassword);
            return tryLogin(connection, username, saltedPassword);

        } catch (UnauthorizedRequestException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean tryLogin(Database db, String username, String saltedPassword) throws SQLException, UnauthorizedRequestException {
        List<Map<String, String>> results = db.query(getLoginQuery(), username, saltedPassword);
        if (results.size() >= 1) {
            this.userID = Integer.parseInt(results.get(0).get("userID"));
            return true;
        } else {
            throw new UnauthorizedRequestException();
        }
    }

    private String fetchSalt(Database db, String username) throws SQLException, UnauthorizedRequestException {
        List<Map<String, String>> results;
        results = db.query(getSaltQuery(), username, username);
        if (results.size() >= 1) {
            return results.get(0).get("salt");
        } else {
            // If we can't look up the salt, the user doesn't exist
            throw new UnauthorizedRequestException();
        }
    }

    private String sha256(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * Danger: DO NOT insert any user supplied data into a query directly.
     *         It will defeat the purpose of using prepared statements.
     *         Instead, use the '?' symbol and bind the parameter.
     */
    private String getLoginQuery() {
        return "SELECT userID " +
                "FROM users " +
                "WHERE nickname = ? AND password = ?";
    }
    private String getSaltQuery() {
        return "SELECT salt " +
                "FROM users " +
                "WHERE nickname = ? OR email = ?";
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public LoginRequest() {
        this.requestType = "login";
    }

}