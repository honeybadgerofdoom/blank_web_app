package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.database.Database;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Base64;

public class LoginRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(LoginRequest.class);
    private String username;
    private String password;
    private boolean success;
    private ArrayList<String> echo;

    @Override
    public void buildResponse() {
        echo = new ArrayList<>();
        echo.add(this.username);
        echo.add(sha256(this.password));
        success = login(this.username, this.password);
        log.trace("buildResponse -> {}", this);
    }

    public boolean login(String username, String password) {
        try (Database db = new Database()) {

            //db.query(query, username, )
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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