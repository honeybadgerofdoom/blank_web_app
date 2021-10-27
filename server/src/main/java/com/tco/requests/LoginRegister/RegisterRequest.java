package com.tco.requests.LoginRegister;

import com.tco.database.Database;
import com.tco.misc.BadRequestException;
import com.tco.requests.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

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
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryRegister(Database db, String username, String email, String saltedPassword, String salt)
            throws SQLException, BadRequestException {

        int rowsUpdated = db.update(getRegisterQuery(), username, email, saltedPassword, salt);
        if (rowsUpdated == 0) {
            throw new BadRequestException();
        }
    }

    private String getRegisterQuery() {
        return "INSERT INTO users (nickname, email, password, salt) " +
                "VALUES (?, ?, ?, ?)";
    }

    /* The following methods exist only for testing purposes and are not used
       during normal execution, including the constructor. */

    public RegisterRequest() {
        this.requestType = "register";
    }
}