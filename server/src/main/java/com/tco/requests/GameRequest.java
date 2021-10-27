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
        String game = getGameIDsFromDB(this.userID);
        success = true;
        log.trace("buildResponse -> {}", this);
    }

    protected static String getGameIDsFromDB(int userID) {
        String boardQuery = getDBQueryString();
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(boardQuery, userID, userID);
            System.out.println(results);
            String board = results.get(0).get("board");
            return board;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "----------------------------------------------------------------";
    }

    private static String getDBQueryString() {
        return "SELECT * FROM games WHERE player1 = ? OR player2 = ?";
    }

}
