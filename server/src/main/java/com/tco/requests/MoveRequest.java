package com.tco.requests;

import java.util.HashMap;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import com.tco.chess.ChessBoard;
import com.tco.chess.ChessPiece;
import com.tco.requests.BoardRequest;
import com.tco.chess.IllegalPositionException;

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
            String boardString = buildNewBoardString();

            //FIXME add winner, castling, promotion
        } catch (IllegalPositionException | IllegalMoveExcpetion e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }

    private String buildNewBoardString() {
        String newBoardString = "";
        HashMap<String, String> unicodeToChar = getUnicodeToChar();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                newBoardString += unicodeToChar.get(board.getPiece(DO THIS))
            }
        }
    }
    
    private HashMap<String, String> getUnicodeToChar() {
        HashMap<String, String> unicodeToChar = new HashMap<String, String>();
        charToUnicode.put("\u2654", "k");
        charToUnicode.put("\u2655", "q");
        charToUnicode.put("\u2656", "r");
        charToUnicode.put("\u2657", "b");
        charToUnicode.put("\u2658", "n");
        charToUnicode.put("\u2659", "p");
        charToUnicode.put("\u265A", "K");
        charToUnicode.put("\u265B", "Q");
        charToUnicode.put("\u265C", "R");
        charToUnicode.put("\u265D", "B");
        charToUnicode.put("\u265E", "N");
        charToUnicode.put("\u265F", "P");
        charToUnicode.put("", "-");
        return unicodeToChar;
    }

}
