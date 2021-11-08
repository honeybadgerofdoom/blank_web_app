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
                String sender = idToNickname(db, Integer.parseInt(results.get(i).get("sender")));
                String status = results.get(i).get("status");
                int gameID = Integer.parseInt(results.get(i).get("gameID"));
                Invite currentInvite = new Invite(sender, status, gameID);
                this.invites.add(currentInvite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String idToNickname(Database db, int enemyID) throws Exception {
        String query = "SELECT nickname FROM users WHERE userID=?";
        List<Map<String, String>> results = db.query(query, enemyID);
        return results.get(0).get("nickname");
    }

    private class Invite {
        private String sender;
        private String status;
        private int gameID;

        public Invite(String sender, String status, int gameID) {
            this.sender = sender;
            this.status = status;
            this.gameID = gameID;
        }
    }

}
