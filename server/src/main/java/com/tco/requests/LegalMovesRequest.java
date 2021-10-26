package com.tco.requests;

import java.util.HashMap;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import com.tco.chess.ChessBoard;

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

public class LegalMovesRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(BoardRequest.class);
    private String position;
    private int userID;

    private String[] legalMoves;

    @Override
    public void buildResponse() {
        //FIXME Write a 1-arg initialize() method in ChessBoard to build a board based on an input string. Call that
        //    from here and check legal moves at position.
        String boardString = getBoardFromDatabase();
        String[] boardState = dbResponseToPieceArray(boardString);
        // try {
        //      ChessBoard board = new ChessBoard();
        //      board.initialize(boardString);
        //      legalMoves = board.getPiece(position).legalMoves();
        // } catch (IllegalPositionException e) {
        //      e.printStackTrace();
        // }
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
        return "----------------------------------------------------------------";
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
