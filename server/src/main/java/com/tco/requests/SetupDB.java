package com.tco.requests;

import com.tco.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetupDB extends Request {

    private final transient Logger log = LoggerFactory.getLogger(SetupDB.class);

    private boolean success;

    @Override
    public void buildResponse() {
//        Database db = new Database();
//        db.addUserTable(); // Adds an example user table
//        db.addUser("User2", "DEVELOPER", "fake_email2@gmail.com", "password"); // Adds an example user
        success = true;
        log.trace("buildResponse -> {}", this);
    }

}
