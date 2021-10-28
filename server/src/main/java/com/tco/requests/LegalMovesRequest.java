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

    private ArrayList<String> legalMoves;

    @Override
    public void buildResponse() {
        String boardString = BoardRequest.getBoardFromDatabase(this.userID);
         try {
              ChessBoard board = new ChessBoard();
              board.initialize(boardString);
              ChessPiece piece = board.getPiece(position);
              legalMoves = piece.legalMoves();
         } catch (IllegalPositionException e) {
              e.printStackTrace();
         }
        log.trace("buildResponse -> {}", this);
    }

}
