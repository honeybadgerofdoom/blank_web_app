package com.tco.requests;

import java.util.Arrays;
import java.util.ArrayList;
import com.tco.chess.ChessBoard;
import com.tco.chess.IllegalPositionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegalMovesRequest extends Request {
    private String position;
    private ArrayList<String> legalMoves;
    private final transient Logger log = LoggerFactory.getLogger(LegalMovesRequest.class);

    @Override
    public void buildResponse() {
        ChessBoard board = new ChessBoard();
        board.initialize();
        try {
            legalMoves = board.getPiece(position).legalMoves();
        } catch(IllegalPositionException e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }
}
