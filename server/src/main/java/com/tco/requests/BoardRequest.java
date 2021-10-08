package com.tco.requests;

import java.util.Arrays;
import java.util.ArrayList;
import com.tco.requests.Board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardRequest extends Request {
    private String[] boardString;
    private final transient Logger log = LoggerFactory.getLogger(BoardRequest.class);

    @Override
    public void buildResponse() {
        //FIXME This is temporary. Eventually we need this to reference a specific board that already exists. That will come.
        Board board = new Board();
        boardString = board.getBoard();
        log.trace("buildResponse -> {}", this);
    }
}
