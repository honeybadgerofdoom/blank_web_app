package com.tco.requests;

import java.util.HashMap;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import com.tco.chess.ChessBoard;
import com.tco.chess.ChessPiece;
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
    // priavate Color userColor; we need this for castling reasons

    private boolean success;
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
            board.move(fromPosition, toPosition);
            success = true;

            // FIXME store new board state!
            String newBoardString = buildNewBoardString(board);
            System.out.println("newBoardString: " + newBoardString);
            // Insert that into the database at the right location

            //FIXME add winner, castling, promotion
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }

    private String buildNewBoardString(ChessBoard board) {
        String newBoardString = "";
        HashMap<String, String> unicodeToChar = getUnicodeToChar();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                String position = rowColToPosition(i, j);
                try {
                    String currentPieceChar = unicodeToChar.get(board.getPiece(position));
                    newBoardString += currentPieceChar;
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }
            }
        }
        return newBoardString;
    }

    private String rowColToPosition(int row, int column) {
        char letter = (char) (column + 97);
        int newRow = row + 1;
        return letter + "" + newRow;
    }
    
    private HashMap<String, String> getUnicodeToChar() {
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
