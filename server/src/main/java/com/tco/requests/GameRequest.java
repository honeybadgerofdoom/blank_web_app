package com.tco.requests;

import com.tco.database.Database;

import java.sql.SQLException;
import java.util.ArrayList;
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

        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(boardQuery, userID, userID);

            for (Map<String, String> gameRow : results) {
                int gameID = Integer.parseInt(gameRow.get("gameID"));
                addGame(db, gameID, gameRow);
            }
        }
    }

    private void addGame(Database db, int gameID, Map<String, String> gameRow) throws SQLException {
        int enemyID = getOpponent(gameRow);
        boolean gameIsActive = enemyID != -1;

        String enemyName = "";
        if (gameIsActive) {
            enemyName = idToNickname(db, enemyID);
        }

        String expectedType = this.type;
        if (expectedType.equals("PENDING") && gameIsActive || expectedType.equals("ACTIVE") && !gameIsActive) {
            return;
        }

        int totalInvites = getTotalInvites(db, gameID);
        String myColor = !gameIsActive ? "WHITE" : getMyColor(gameRow);
        boolean myTurn = !gameIsActive || checkMyTurn(gameRow, enemyID);
        this.games.add(new Game(gameID, enemyName, totalInvites, myColor, myTurn));
    }

    public int getTotalInvites(Database db, int gameID) throws SQLException {
        String invitationCountQuery = "SELECT COUNT(*) as totalInvites FROM invites WHERE gameID = ?";

        List<Map<String, String>> invitationResults = db.query(invitationCountQuery, gameID);
        return Integer.parseInt(invitationResults.get(0).get("totalInvites"));
    }

    public String getMyColor(Map<String, String> gameRow) {
        int player1 = Integer.parseInt(gameRow.get("player1"));
        return userID == player1 ? "WHITE" : "BLACK";
    }

    public boolean checkMyTurn(Map<String, String> gameRow, int enemyID) {
        int player2 = Integer.parseInt(gameRow.get("player2"));
        boolean userIsPlayer1 = (enemyID == player2);
        String turn = gameRow.get("turn");
        boolean player1Turn = turn.equals("WHITE");

        return userIsPlayer1 && player1Turn || !userIsPlayer1 && !player1Turn;
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
        String myColor;
        boolean myTurn;

        public Game(int gameID, String opponentName, int totalInvites, String myColor, boolean myTurn) {
            this.gameID = gameID;
            this.opponentName = opponentName;
            this.totalInvites = totalInvites;
            this.myColor = myColor;
            this.myTurn = myTurn;
        }
    }
}
