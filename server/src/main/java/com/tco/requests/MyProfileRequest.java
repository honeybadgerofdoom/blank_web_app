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

public class MyProfileRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MyProfileRequest.class);
    private int userID;

    private String nickname;
    private String email;
    private String bio;
    private String picURL;

    @Override
    public void buildResponse() {
        extractUserInfo();
        log.trace("buildResponse -> {}", this);
    }

    private void extractUserInfo() {
        String query = "SELECT * FROM users WHERE userID=?";
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(query, this.userID);
            Map<String,String> result = results.get(0);
            this.nickname = result.get("nickname");
            this.email = result.get("email");
            this.bio = result.get("bio");
            this.picURL = result.get("picURL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
