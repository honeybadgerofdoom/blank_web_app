package com.tco.requests;

import java.util.ArrayList;
import java.util.Arrays;
import com.tco.chess.ChessBoard;
import com.tco.chess.IllegalPositionException;
import com.tco.chess.IllegalMoveException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This will need to be changed to reflect the current state of a given board.

public class Board {
    private String[] boardString;

    public Board() {
        ChessBoard board = new ChessBoard();
        board.initialize();
        try {
            board.move("d2", "d4");
            board.move("a7", "a5");
            board.move("a8", "a6");
            board.move("f2", "f3");
            board.move("b2", "b4");
            board.move("h7", "h6");
        } catch(IllegalMoveException e) {
            e.printStackTrace();
        }
        try {
            boardString = buildBoardString(board);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    public String[] getBoard() {
        return boardString;
    }

    private String[] buildBoardString(ChessBoard board) throws IllegalPositionException {
        ArrayList<String> tempBoardString = new ArrayList<String>();
        for(int row = 0; row < 8; row++) {
            for(int column = 0; column < 8; column++) {
                if(board.getPiece(arrayToString(row, column)) != null) {
                    tempBoardString.add(board.getPiece(arrayToString(row, column)).toString());
                }
                else {
                    tempBoardString.add("");
                }
            }
        }
        int count = 0;
        String[] listToArray = new String[64];
        for(String position : tempBoardString) {
            listToArray[count] = position;
            count++;
        }
        return listToArray;
    }

    private String arrayToString(int row, int column) {
        char letter = (char) (column + 97);
        int newRow = row + 1;
        return letter + "" + newRow;
    }
}
