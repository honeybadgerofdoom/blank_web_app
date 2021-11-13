package com.tco.requests.Users;

import java.util.HashMap;
import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import com.tco.requests.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateUserInfoRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(UpdateUserInfoRequest.class);
    private int userID;
    private String nickname;
    private String email;
    private String bio;
    private String picURL;

    private boolean success;

    @Override
    public void buildResponse() {
        updateInfo();
        log.trace("buildResponse -> {}", this);
    }

    private void updateInfo() {
        String query = "UPDATE users SET nickname=?, email=?, bio=?, picURL=? WHERE userID=?";
        try (Database db = new Database()) {
            db.update(query, this.nickname, this.email, this.bio, this.picURL, this.userID);
            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
    }



}
