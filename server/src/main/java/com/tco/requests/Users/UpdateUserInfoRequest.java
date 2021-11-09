package com.tco.requests;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyInvitesRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MyInvitesRequest.class);
    private int userID;
    private String nickname;
    private String email;
    private String bio;
    private String picURL;

    private boolean success;

    @Override
    public void buildResponse() {
        invites = new ArrayList<Invite>();
        updateInfo();
        log.trace("buildResponse -> {}", this);
    }

    private void updateInfo() {
        String query = "UPDATE users SET (nickname, email, bio, picURL) VALUES (?, ?, ?, ?) WHERE userID=?";
        try (Database db = new Database()) {
            db.update(query, this.nickname, this.email, this.bio, this.picURL, this.userID);
            this.success = success;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    }



}
