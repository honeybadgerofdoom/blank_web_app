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

    private final transient Logger log = LoggerFactory.getLogger(AcceptInviteRequest.class);
    private int gameID;
    private int senderID;
    private int recieverID;


    @Override
    public void buildResponse() {
        deleteFromInviteTable();
        addPlayerteo();
        log.trace("buildResponse -> {}", this);
    }

    private void deleteFromInviteTable() {
        String query = "DELETE FROM invites WHERE gameID=? AND sender=? AND receiver=?";
        try (Database db = new Database()) {
            senderID = nicknameToID(db, this.sender);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPlayerTwo(){
        String queryForPlayer = "SELECT gameID=? FROM games WHERE ";
            try (Database db = new Database()) {
                int senderID = nicknameToID(db, this.sender);
            
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
