package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.database.Database;
import java.util.ArrayList;

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
        echo.add(this.password);
        success = login(this.username, this.password);
        log.trace("buildResponse -> {}", this);
    }

    public boolean login(String username, String password) {
        return false;
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public LoginRequest() {
        this.requestType = "login";
    }

}