package com.tco.requests;

import java.util.Arrays;
import java.util.ArrayList;
import com.tco.chess.ChessBoard;
import com.tco.chess.IllegalPositionException;
import com.tco.chess.IllegalMoveException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegalMovesRequest extends Request {
    private String position;
    private ArrayList<String> legalMoves;
    private final transient Logger log = LoggerFactory.getLogger(LegalMovesRequest.class);

    @Override
    public void buildResponse() {
        //FIXME This is temporary. Eventually we need this to reference a specific board that already exists. That will come.
        ChessBoard board = new ChessBoard();
        board.initialize();
        try {
            board.move("d2", "d4");
            board.move("a7", "a5");
            board.move("a8", "a6");
        } catch(IllegalMoveException e) {
            e.printStackTrace();
        }
        try {
            legalMoves = board.getPiece(position).legalMoves();
        } catch(IllegalPositionException e) {
            e.printStackTrace();
        }
        log.trace("buildResponse -> {}", this);
    }
}
