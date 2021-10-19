package com.tco.requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.tco.chess.ChessBoard;
import com.tco.chess.IllegalPositionException;
import com.tco.chess.IllegalMoveException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This will need to be changed to reflect the current state of a given board.

public class Board {
    private String[] boardString;
    private HashMap<String, String> pieceMapping = new HashMap<String, String>();
    private HashMap<String, String> pieceReMapping = new HashMap<String, String>();

    public Board() {
        pieceMapping.put("\u2654", "k");
        pieceMapping.put("\u2655", "q");
        pieceMapping.put("\u2656", "r");
        pieceMapping.put("\u2657", "b");
        pieceMapping.put("\u2658", "n");
        pieceMapping.put("\u2659", "p");
        pieceMapping.put("\u265A", "K");
        pieceMapping.put("\u265B", "Q");
        pieceMapping.put("\u265C", "R");
        pieceMapping.put("\u265D", "B");
        pieceMapping.put("\u265E", "N");
        pieceMapping.put("\u265F", "P");
        pieceMapping.put("", "-");

        pieceReMapping.put("k", "\u2654");
        pieceReMapping.put("q", "\u2655");
        pieceReMapping.put("r", "\u2656");
        pieceReMapping.put("b", "\u2657");
        pieceReMapping.put("n", "\u2658");
        pieceReMapping.put("p", "\u2659");
        pieceReMapping.put("K", "\u265A");
        pieceReMapping.put("Q", "\u265B");
        pieceReMapping.put("R", "\u265C");
        pieceReMapping.put("B", "\u265D");
        pieceReMapping.put("N", "\u265E");
        pieceReMapping.put("P", "\u265F");
        pieceReMapping.put("-", "");

        ChessBoard board = new ChessBoard();
        board.initialize();
        try {
            board.move("d2", "d4");
            board.move("a7", "a5");
            board.move("a8", "a6");
            board.move("f2", "f3");
            board.move("b2", "b4");
            board.move("h7", "h6");
            board.move("d1", "d3");
            board.move("d7", "d5");
            board.move("d8", "d6");
            board.move("b1", "a3");
            board.move("c1", "g5");
            board.move("g7", "g6");
            board.move("f8", "g7");
            board.move("g8", "f6");
            board.move("h8", "f8");
            board.move("f8", "h8");
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
        System.out.println("tempBoardString: " + tempBoardString);

        // This is what the database will actually be responding with. Turn this into a String array of size 64
        String mockedDatabaseResponse = mockDatabaseBoardStateResponse(tempBoardString);
        System.out.println("mockedDatabaseResponse: " + mockedDatabaseResponse);

        // This is what we actually want to send to the client
        String[] arrayOfPieces = dbResponseToPieceArray(mockedDatabaseResponse);
        System.out.println("arrayOfPieces: " + Arrays.toString(arrayOfPieces));

        return arrayOfPieces;
    }

    private String[] dbResponseToPieceArray(String dbResponse) {
        char[] charArray = dbResponse.toCharArray();
        String[] arrayOfPieces = new String[64];
        int count = 0;
        for (char piece : charArray) {
            arrayOfPieces[count] = pieceReMapping.get(String.valueOf(piece));
            count++;
        }
        return arrayOfPieces;
    }

    private String mockDatabaseBoardStateResponse(ArrayList<String> initialBoardState) {
        String mockDBResponse = "";
        for (String entry : initialBoardState) {
            mockDBResponse += pieceMapping.get(entry);
        }
        return mockDBResponse;
    }

    private String arrayToString(int row, int column) {
        char letter = (char) (column + 97);
        int newRow = row + 1;
        return letter + "" + newRow;
    }
}
