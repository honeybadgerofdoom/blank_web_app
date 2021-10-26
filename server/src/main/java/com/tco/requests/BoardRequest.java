package com.tco.requests;

import com.tco.requests.Board;
import java.util.HashMap;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardRequest extends Request {
    
    private final transient Logger log = LoggerFactory.getLogger(BoardRequest.class);
    private int userID;

    private String[] boardString;
    private boolean success;
    
    @Override
    public void buildResponse() {
        String boardState = getBoardFromDatabase();
        boardString = dbResponseToPieceArray(boardState);
        success = true;
        log.trace("buildResponse -> {}", this);
    }

    private String getBoardFromDatabase() {
        String boardQuery = getDBQueryString();
        Database db = new Database();
        try {
            List<Map<String, String>> results = db.query(boardQuery);
            String board = results.get(0).get("board");
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getDBQueryString() {
        return "SELECT * FROM games WHERE player1=" + this.userID + " OR player2=" + this.userID;
    }

    private String[] dbResponseToPieceArray(String dbResponse) {
        char[] charArray = dbResponse.toCharArray();
        String[] arrayOfPieces = new String[64];
        HashMap<String, String> charToUnicode = getPieceMapping();
        int count = 0;
        for (char piece : charArray) {
            arrayOfPieces[count] = charToUnicode.get(String.valueOf(piece));
            count++;
        }
        return arrayOfPieces;
    }

    private HashMap<String, String> getPieceMapping() {
        HashMap<String, String> charToUnicode = new HashMap<String, String>();
        charToUnicode.put("k", "\u2654");
        charToUnicode.put("q", "\u2655");
        charToUnicode.put("r", "\u2656");
        charToUnicode.put("b", "\u2657");
        charToUnicode.put("n", "\u2658");
        charToUnicode.put("p", "\u2659");
        charToUnicode.put("K", "\u265A");
        charToUnicode.put("Q", "\u265B");
        charToUnicode.put("R", "\u265C");
        charToUnicode.put("B", "\u265D");
        charToUnicode.put("N", "\u265E");
        charToUnicode.put("P", "\u265F");
        charToUnicode.put("-", "");
        return charToUnicode;
    }

}
