package com.tco.requests;

import java.util.HashMap;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import com.tco.chess.ChessBoard;
import com.tco.chess.ChessPiece;
import com.tco.chess.ChessPiece.Color;
import com.tco.requests.BoardRequest;
import com.tco.chess.IllegalPositionException;
import com.tco.chess.IllegalMoveException;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(BoardRequest.class);
    private String fromPosition;
    private String toPosition;
    private int userID;
    // priavate Color userColor; we need this for castling & turn reasons

    private boolean turnValid;
    private boolean verifyPlayerColor;
    private String[] newBoardState;
    // private boolean kingsideCastle;
    // private boolean queensideCastle;
    // private boolean promotion;
    // private Color winner;

    @Override
    public void buildResponse() {
        String boardString = BoardRequest.getBoardFromDatabase(this.userID);
        /*
        FIXME If player is player1, they can move WHITE pieces, if player2, they can move BLACK piece

        1) Get player color via db query
        2) Get piece color via getPiece(fromPosition).getColor()
        3) Compare colors, set response var verifyPlayerColor
        4) Check that response var on client
         */
        try {
            ChessBoard board = new ChessBoard();
            board.initialize(boardString);
            String pieceColor = board.getPiece(fromPosition).getColor() == Color.WHITE ? "WHITE" : "BLACK";
            int gameID = getGameID();
            String turn = getTurnFromDB(gameID);
            String playerColor = getPlayerColor(gameID);
            if(playerColor.equals(pieceColor)) {
                verifyPlayerColor = true;
                if (turn.equals(pieceColor)) {
                    turnValid = true;
                    board.move(fromPosition, toPosition);
                    String newBoardString = buildNewBoardString(board);
                    turn = turn.equals("WHITE") ? "BLACK" : "WHITE";
                    storeNewBoardState(newBoardString, turn, gameID);
                }
                else {
                    turnValid = false;
                    String newBoardString = buildNewBoardString(board);
                    storeNewBoardState(newBoardString, turn, gameID);
                }
            }
            else {
                verifyPlayerColor = false;
                turnValid = false;
                String newBoardString = buildNewBoardString(board);
                storeNewBoardState(newBoardString, turn, gameID);
            }

            //FIXME add winner, castling, promotion
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }

    private String getPlayerColor(int gameID) throws Exception {
        int[] players = extractPlayers(gameID);
        if(this.userID == players[0]) return "WHITE";
        else if(this.userID == players[1]) return "BLACK";
        else return "";
    }

    private int getGameID() throws Exception {
        try (Database db = new Database()) {
            List<Map<String, String>> gameIDResults = db.query(getFirstGameID(), this.userID, this.userID);
            return Integer.parseInt(gameIDResults.get(0).get("gameID"));
        }
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
            System.out.println("Results: " + results);
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

    private String getFirstGameID() {
        return "SELECT gameID FROM games WHERE player1=? OR player2=? LIMIT 1";
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
        this.newBoardState = BoardRequest.dbResponseToPieceArray(newBoardString);
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
