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
    private String type;
    private boolean success;

    @Override
    public void buildResponse() throws Exception {
        if (type == null) {
            type = "ALL";
        }

        games = new ArrayList<>();
        getGameIDsFromDB(this.userID);
        success = true;
        log.trace("buildResponse -> {}", this);
    }

    private void getGameIDsFromDB(int userID) throws Exception {
        String boardQuery = "SELECT * FROM games WHERE player1 = ? OR player2 = ?";
        String invitationCountQuery = "SELECT COUNT(*) as totalInvites FROM invites WHERE gameID = ?";
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(boardQuery, userID, userID);

            for (Map<String, String> gameRow : results) {
                int gameID = Integer.parseInt(gameRow.get("gameID"));
                int enemyID = getOpponent(gameRow);

                List<Map<String, String>> invitationResults = db.query(invitationCountQuery, gameID);
                int totalInvites = Integer.parseInt(invitationResults.get(0).get("totalInvites"));

                addGame(db, gameID, enemyID, totalInvites);
            }
        }
    }

    private void addGame(Database db, int gameID, int enemyID, int totalInvites) throws SQLException {
        boolean gameIsActive = enemyID != -1;

        String enemyName = "";
        if (gameIsActive) {
            enemyName = idToNickname(db, enemyID);
        }

        String expectedType = this.type;
        if (expectedType.equals("PENDING") && gameIsActive || expectedType.equals("ACTIVE") && !gameIsActive) {
            return;
        }

        this.games.add(new Game(gameID, enemyName, totalInvites));
    }

    public static String idToNickname(Database db, int enemyID) throws SQLException {
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

    private static class Game {
        int gameID;
        String opponentName;
        int totalInvites;

        public Game(int gameID, String opponentName, int totalInvites) {
            this.gameID = gameID;
            this.opponentName = opponentName;
            this.totalInvites = totalInvites;
        }
    }
}
