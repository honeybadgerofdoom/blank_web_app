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

public class GameRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(GameRequest.class);
    private int userID;

    private ArrayList<Integer> gameIDs;
    private boolean success;

    @Override
    public void buildResponse() {
        gameIDs = new ArrayList<Integer>();
        getGameIDsFromDB(this.userID);
        success = true;
        log.trace("buildResponse -> {}", this);
    }

    private void getGameIDsFromDB(int userID) {
        String boardQuery = getDBQueryString();
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(boardQuery, userID, userID);

            for(int i = 0; i < results.size(); i++){
                this.gameIDs.add(Integer.parseInt(results.get(i).get("gameID")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDBQueryString() {
        return "SELECT * FROM games WHERE player1 = ? OR player2 = ?";
    }

}
