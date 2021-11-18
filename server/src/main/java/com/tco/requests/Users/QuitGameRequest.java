package com.tco.requests;

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

public class QuitGameRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(QuitGameRequest.class);
    private int gameID;
    private int currentUser;
    private int player2;
    private String sender;
    private boolean success;


    @Override
    public void buildResponse() {
        currentUserLost();
        secondPlayerWin();
        log.trace("buildResponse -> {}", this);
    }

    private void currentUserLost() {
        String query = "DELETE FROM invites WHERE gameID=? AND sender=?";
        try (Database db = new Database()) {
            senderID = nicknameToID(db, this.sender);
            db.update(query, this.gameID, senderID);
            this.success = true;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    }

    private void secondPlayerWin(){
        String queryForPlayer = "UPDATE games SET player2=? WHERE gameID=?";
            try (Database db1 = new Database()) {
                db1.update(queryForPlayer, this.player2, this.gameID);
                this.success = true;
            } catch (Exception e) {
                this.success = false;
                e.printStackTrace();
            }
    }

    private int nicknameToID(Database db, String nickname) throws Exception {
        String query = "SELECT userID FROM users WHERE nickname=?";
        List<Map<String, String>> results = db.query(query, nickname);
        return Integer.parseInt(results.get(0).get("userID"));
    }


}
