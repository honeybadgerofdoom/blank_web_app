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
    private String[] newBoardState;
    // private boolean kingsideCastle;
    // private boolean queensideCastle;
    // private boolean promotion;
    // private Color winner;

    @Override
    public void buildResponse() {
        String boardString = BoardRequest.getBoardFromDatabase(this.userID);
        try {
            ChessBoard board = new ChessBoard();
            board.initialize(boardString);
            String pieceColor = board.getPiece(fromPosition).getColor() == Color.WHITE ? "WHITE" : "BLACK";
            String turn = getTurnFromDB();
            if (turn.equals(pieceColor)) {
                turnValid = true;
                board.move(fromPosition, toPosition);
                String newBoardString = buildNewBoardString(board);
                turn = turn.equals("WHITE") ? "BLACK" : "WHITE";
                storeNewBoardState(newBoardString, turn);
            } else {
                turnValid = false;
                String newBoardString = buildNewBoardString(board);
                storeNewBoardState(newBoardString, turn);
            }

            //FIXME add winner, castling, promotion
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }

    private String getTurnFromDB() {
        try (Database db = new Database()) {
            List<Map<String, String>> gameIDResults = db.query(getFirstGameID(), this.userID, this.userID);
            int gameID = Integer.parseInt(gameIDResults.get(0).get("gameID"));

            List<Map<String, String>> results = db.query(getTurnQueryFromDB(), gameID);
            String turn = results.get(0).get("turn");
            return turn;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void storeNewBoardState (String newBoardString, String turn) throws Exception {
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(getFirstGameID(), this.userID, this.userID);
            int gameID = Integer.parseInt(results.get(0).get("gameID"));
            db.update(storingQueryString(), newBoardString, gameID);
            db.update(storeTurnIntoDB(), turn, gameID);
        }
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
