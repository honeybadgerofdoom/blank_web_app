package com.tco.requests;

import java.util.ArrayList;
import com.tco.chess.ChessBoard;
import com.tco.chess.IllegalPositionException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This will need to be changed to reflect the current state of a given board.

public class Board {
    private String[] boardString;

    public Board() {
        ChessBoard board = new ChessBoard();
        board.initialize();
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
        String[] tempBoardString = new String[64];
        int currentLocation = -2;
        for(int row = 0; row < 8; row++) {
            currentLocation ++;
            for(int column = 0; column < 8; column++) {
                currentLocation ++;
                if(board.getPiece(arrayToString(row, column)) != null) {
                    tempBoardString[currentLocation] = board.getPiece(arrayToString(row, column)).toString();
                }
                else {
                    tempBoardString[currentLocation] = "";
                }
            }
        }
        return tempBoardString;
    }

    private String arrayToString(int row, int column) {
        char letter = (char) (column + 97);
        int newRow = row + 1;
        return letter + "" + newRow;
    }
}
