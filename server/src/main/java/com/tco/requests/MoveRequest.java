package com.tco.requests;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import com.tco.chess.ChessPiece.Color;
import com.tco.requests.BoardRequest;
import com.tco.chess.*;
import com.tco.requests.*;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(BoardRequest.class);
    private String fromPosition;
    private String toPosition;
    private int userID;
    private int gameID;

    private boolean turnValid;
    private boolean verifyPlayerColor;
    private String[] newBoardState;
    private String winner;
    private String loser;

    @Override
    public void buildResponse() {
        String boardString = BoardRequest.getBoardFromDatabase(this.gameID);
        try {
            ChessBoard board = new ChessBoard();
            board.initialize(boardString);
            String pieceColor = board.getPiece(fromPosition).getColor() == Color.WHITE ? "WHITE" : "BLACK";
            String turn = getTurnFromDB(gameID);
            String playerColor = getPlayerColor(gameID);
            if(playerColor.equals(pieceColor)) {
                verifyPlayerColor = true;
                if (turn.equals(pieceColor)) {
                    turnValid = true;
                    board.move(fromPosition, toPosition);
                    String newBoardString = buildNewBoardString(board);
                    int flag = checkIfGameIsOver(newBoardString);
                    turn = turn.equals("WHITE") ? "BLACK" : "WHITE";
                    storeNewBoardState(newBoardString, turn, gameID);
                }
                else {
                    turnValid = false;
                    this.newBoardState = BoardRequest.boardStringToBoardState(boardString);
                }
            }
            else {
                verifyPlayerColor = false;
                turnValid = false;
                this.newBoardState = BoardRequest.boardStringToBoardState(boardString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }

    private int checkIfGameIsOver(String newBoardState) throws Exception {
        System.out.println(newBoardState);
        int[] piecesRemaining = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        char[] board = newBoardState.toCharArray();
        for(char piece : board) {
            switch (piece) {
                case 'r' : piecesRemaining[0]++; break;
                case 'n' : piecesRemaining[1]++; break;
                case 'b' : piecesRemaining[2]++; break;
                case 'q' : piecesRemaining[3]++; break;
                case 'k' : piecesRemaining[4]++; break;
                case 'p' : piecesRemaining[5]++; break;
                case 'R' : piecesRemaining[6]++; break;
                case 'N' : piecesRemaining[7]++; break;
                case 'B' : piecesRemaining[8]++; break;
                case 'Q' : piecesRemaining[9]++; break;
                case 'K' : piecesRemaining[10]++; break;
                case 'P' : piecesRemaining[11]++; break;
                default: break;
            }
        }

        int[] players = extractPlayers(this.gameID);
        int player1ID = players[0];
        int player2ID = players[1];

        for(int i = 0; i < 12; i++) {
            if(piecesRemaining[i] < 1) {
                int winner = i < 6 ? player2ID : player1ID;
                int loser = i < 6 ? player1ID : player2ID;
                storeWinnerLoser(winner, loser);
                return 1;
            }
        }
        return 0;
    }

    private void storeWinnerLoser(int winnerID, int loserID) throws Exception {
        String updateWinner = "UPDATE users SET wins=wins+1 WHERE userID=?";
        String updateLoser = "UPDATE users SET losses=losses+1 WHERE userID=?";
        String deleteGame = "DELETE FROM games WHERE gameID=?";

        try (Database db = new Database()) {
            db.update(updateWinner, winnerID);
            db.update(updateLoser, loserID);
            db.update(deleteGame, gameID);
            this.winner = GameRequest.idToNickname(db, winnerID);
            this.loser = GameRequest.idToNickname(db, loserID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPlayerColor(int gameID) throws Exception {
        int[] players = extractPlayers(gameID);
        if(this.userID == players[0]) return "WHITE";
        else if(this.userID == players[1]) return "BLACK";
        else return "";
    }

    private String getTurnFromDB(int gameID) {
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(getTurnQueryFromDB(), gameID);
            String turn = results.get(0).get("turn");
            return turn;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void storeNewBoardState (String newBoardString, String turn, int gameID) throws Exception {
        try (Database db = new Database()) {
            db.update(storingQueryString(), newBoardString, gameID);
            db.update(storeTurnIntoDB(), turn, gameID);
        }
    }

    private int[] extractPlayers(int gameID) throws Exception {
        int[] players = new int[2];
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(getPlayerColorFromDB(), gameID);
            int player1 = Integer.parseInt(results.get(0).get("player1"));
            int player2 = Integer.parseInt(results.get(0).get("player2"));
            players[0] = player1;
            players[1] = player2;
            return players;
        }
    }

    private String getPlayerColorFromDB() {
        return "SELECT * FROM games WHERE gameID=?";
    }

    private String getTurnQueryFromDB() {
        return "SELECT turn FROM games WHERE gameID=?";
    }

    private String storeTurnIntoDB() {
        return "UPDATE games set turn=? WHERE gameID=?";
    }

    private String storingQueryString() {
        return "UPDATE games SET board=? WHERE gameID=?";
    }

    private String buildNewBoardString (ChessBoard board){
        String newBoardString = "";
        HashMap<String, String> unicodeToChar = getUnicodeToChar();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String position = rowColToPosition(i, j);
                try {
                    ChessPiece piece = board.getPiece(position);
                    if (piece != null) {
                        String pieceChar = unicodeToChar.get(piece.toString());
                        newBoardString += pieceChar;
                    } else newBoardString += "-";
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }
            }
        }
        this.newBoardState = BoardRequest.boardStringToBoardState(newBoardString);
        return newBoardString;
    }

    private String rowColToPosition ( int row, int column){
        char letter = (char) (column + 97);
        int newRow = row + 1;
        return letter + "" + newRow;
    }

    private HashMap<String, String> getUnicodeToChar () {
        HashMap<String, String> unicodeToChar = new HashMap<String, String>();
        unicodeToChar.put("\u2654", "k");
        unicodeToChar.put("\u2655", "q");
        unicodeToChar.put("\u2656", "r");
        unicodeToChar.put("\u2657", "b");
        unicodeToChar.put("\u2658", "n");
        unicodeToChar.put("\u2659", "p");
        unicodeToChar.put("\u265A", "K");
        unicodeToChar.put("\u265B", "Q");
        unicodeToChar.put("\u265C", "R");
        unicodeToChar.put("\u265D", "B");
        unicodeToChar.put("\u265E", "N");
        unicodeToChar.put("\u265F", "P");
        unicodeToChar.put("", "-");
        return unicodeToChar;
    }
}
