package com.tco.requests;

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
    private boolean success;
    private int userID = 0;

    @Override
    public void buildResponse() {
        success = login(this.username, this.password);
        log.trace("buildResponse -> {}", this);
    }

    public boolean login(String username, String password) {
        try (Database connection = new Database()) {

            String salt = fetchSalt(connection, username);
            String saltedPassword = sha256(password + salt);
            return tryLogin(connection, username, saltedPassword);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean tryLogin(Database db, String username, String saltedPassword) throws SQLException {
        List<Map<String, String>> results = db.query(getLoginQuery(), username, saltedPassword);
        if (results.size() >= 1) {
            this.userID = Integer.parseInt(results.get(0).get("userID"));
            return true;
        } else {
            return false;
        }
    }

    private String fetchSalt(Database db, String username) throws SQLException {
        List<Map<String, String>> results;
        results = db.query(getSaltQuery(), username);
        if (results.size() >= 1) {
            return results.get(0).get("salt");
        } else {
            return "";
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
                "WHERE nickname = ?";
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public LoginRequest() {
        this.requestType = "login";
    }

}