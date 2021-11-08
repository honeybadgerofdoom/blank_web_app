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

public class NewInviteRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(NewInviteRequest.class);
    private int userID;
    private int gameID;
    private int opponentID;

    private boolean success;

    @Override
    public void buildResponse() {
        createInvite();
        log.trace("buildResponse -> {}", this);
    }

    private void createInvite() {
        String query = "INSERT INTO invites (gameID, sender, receiver, status) VALUES (?, ?, ?, ?)";
        String status = "PENDING";()
        try (Database db = new Database()) {
            db.update(query, this.gameID, this.userID, this.opponentID, status);
            this.success = true;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    }

}
