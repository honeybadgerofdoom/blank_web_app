package com.tco.requests.LoginRegister;

import com.tco.database.Database;
import com.tco.misc.BadRequestException;
import com.tco.requests.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RegisterRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(RegisterRequest.class);

    private String username;
    private String password;
    private String email;
    private int userID;

    @Override
    public void buildResponse() throws BadRequestException {
        register(username, password, email);
        log.trace("buildResponse -> {}", this);
    }

    public void register(String username, String password, String email) throws BadRequestException {
        try (Database db = new Database()) {
            String salt = PasswordUtil.randomSalt();
            String saltedPassword = PasswordUtil.sha256(password + salt);

            tryRegister(db, username, email, saltedPassword, salt);
            userID = getUserID(db, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    private void tryRegister(Database db, String username, String email, String saltedPassword, String salt) throws SQLException {
        db.update(getRegisterQuery(), username, email, saltedPassword, salt);
    }

    private int getUserID(Database db, String username) throws SQLException {
        List<Map<String, String>> results = db.query(getUserIDQuery(), username);
        String userIDStr = results.get(0).get("userID");
        return Integer.parseInt(userIDStr);
    }

    private String getRegisterQuery() {
        return "INSERT INTO users (nickname, email, password, salt) " +
                "VALUES (?, ?, ?, ?)";
    }

    private String getUserIDQuery() {
        return "SELECT userID FROM users WHERE nickname=?";
    }

    /* The following methods exist only for testing purposes and are not used
       during normal execution, including the constructor. */

    public RegisterRequest() {
        this.requestType = "register";
    }
}