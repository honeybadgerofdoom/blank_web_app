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
import com.tco.requests.Invite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyInvitesRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MyInvitesRequest.class);
    private int userID;

    private ArrayList<Invite> invites;

    @Override
    public void buildResponse() {
        invites = new ArrayList<Invite>();
        extractInviteInfo();
        log.trace("buildResponse -> {}", this);
    }

    private void extractInviteInfo() {
        String query = "SELECT * FROM invites WHERE receiver=?";
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(query, this.userID);
            for(int i = 0; i < results.size(); i++){
                String sender = results.get(i).get("sender");
                String receiver = results.get(i).get("receiver");
                String status = results.get(i).get("status");
                Invite currentInvite = new Invite(this.userID, sender, receiver, status);
                this.invites.add(currentInvite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
