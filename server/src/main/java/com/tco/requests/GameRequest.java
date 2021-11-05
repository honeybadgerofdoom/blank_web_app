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

            for(int i = 0; i < results.size(); i++){
               // this.gameIDs.add(Integer.parseInt(results.get(i).get("gameID")));
                int gameID = Integer.parseInt(results.get(i).get("gameID"));
                int enemyID = getOpponent(gameID);
                String enemyName = idToNickname(enemyID);
                this.games.add(new Game(gameID, enemyName));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String idToNickname(int enemyID) {
        String query = "SELECT nickname FROM users WHERE userID=?";
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(query, enemyID);
            return results.get(0).get("nickname");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getOpponent(int gameID){
        String query = "SELECT * FROM games WHERE gameID=?";
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(query, gameID);
            int player1 = Integer.parseInt(results.get(0).get("player1"));
            int player2 = 0;
            if(results.get(0).get("player2") != null){
                player2 = Integer.parseInt(results.get(0).get("player2"));
            }
            return this.userID != player1 ? player1 : player2;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
