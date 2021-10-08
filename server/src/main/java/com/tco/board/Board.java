package com.tco.requests;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(Arrays.toString(listToArray));
        return listToArray;
    }

    private String arrayToString(int row, int column) {
        char letter = (char) (column + 97);
        int newRow = row + 1;
        return letter + "" + newRow;
    }
}
