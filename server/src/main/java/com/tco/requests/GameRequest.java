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
    private ArrayList<Game> games;
    private boolean success;

    @Override
    public void buildResponse() {
        games = new ArrayList<Game>();
        getGameIDsFromDB(this.userID);
        success = true;
        log.trace("buildResponse -> {}", this);
    }

    private void getGameIDsFromDB(int userID) {
        String boardQuery = getDBQueryString();
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(boardQuery, userID, userID);

            for(Map<String, String> gameRow : results){
                int gameID = Integer.parseInt(gameRow.get("gameID"));
                int enemyID = getOpponent(gameRow);

                String enemyName;
                if(enemyID == -1){
                    enemyName = "[Pending]";
                }
                else{
                    enemyName = idToNickname(db, enemyID);
                }
                this.games.add(new Game(gameID, enemyName));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String idToNickname(Database db, int enemyID) throws SQLException{
        String query = "SELECT nickname FROM users WHERE userID=?";
        List<Map<String, String>> results = db.query(query, enemyID);

        return results.get(0).get("nickname");
    }

    private int getOpponent(Map<String, String> gameRow){
        String player1 = gameRow.get("player1");
        String player2 = gameRow.get("player2");

        int id1 = Integer.parseInt(player1);
        int id2 = -1;
        if(player2 != null){
            id2 = Integer.parseInt(player2);
        }

        return this.userID != id1 ? id1 : id2;

    }

    private static String getDBQueryString() {
        return "SELECT * FROM games WHERE player1 = ? OR player2 = ?";
    }

    private class Game{
        int gameID;
        String opponentName;

        public Game(int gameID, String opponentName){
            this.gameID = gameID;
            this.opponentName = opponentName;
        }
    }
}
