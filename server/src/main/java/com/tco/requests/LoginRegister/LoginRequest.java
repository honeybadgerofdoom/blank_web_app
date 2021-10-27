package com.tco.requests.LoginRegister;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.requests.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.database.Database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LoginRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(LoginRequest.class);
    private String username;
    private String password;
    private int userID;

    @Override
    public void buildResponse() throws UnauthorizedRequestException {
        login(this.username, this.password);
        log.trace("buildResponse -> {}", this);
    }

    public void login(String username, String password) throws UnauthorizedRequestException {
        try (Database connection = new Database()) {
            String salt = PasswordUtil.fetchSalt(connection, username);
            if (salt == null) {
                // If we can't look up the salt, the user doesn't exist
                throw new UnauthorizedRequestException();
            }
            String saltedPassword = PasswordUtil.sha256(password + salt);

            tryLogin(connection, username, saltedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryLogin(Database db, String username, String saltedPassword) throws SQLException, UnauthorizedRequestException {
        List<Map<String, String>> results = db.query(getLoginQuery(), username, saltedPassword);
        if (results.size() >= 1) {
            this.userID = Integer.parseInt(results.get(0).get("userID"));
        } else {
            throw new UnauthorizedRequestException();
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

    /* The following methods exist only for testing purposes and are not used
       during normal execution, including the constructor. */

    public LoginRequest() {
        this.requestType = "login";
    }

}