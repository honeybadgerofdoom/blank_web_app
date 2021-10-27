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


            //FIXME add winner, castling, promotion
        } catch (IllegalPositionException | IllegalMoveExcpetion e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }

}
