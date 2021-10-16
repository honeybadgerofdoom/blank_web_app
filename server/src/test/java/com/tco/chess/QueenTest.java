package com.tco.chess;
 
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
 
import com.tco.chess.ChessPiece.Color;
 
class QueenTest {
       private ChessBoard testBoard;
      
       @BeforeEach
       private void createTestBoard() {
           testBoard = new ChessBoard();
       }
 
       @Test
       void testColor_InitialPosition() {
           testBoard.initialize();
          
           try {
               ChessPiece whiteQueen = testBoard.getPiece("d1");
               ChessPiece blackQueen = testBoard.getPiece("d8");
              
               assertTrue(whiteQueen instanceof Queen);
               assertTrue(blackQueen instanceof Queen);
              
               assertTrue(whiteQueen.getColor().equals(Color.WHITE));
               assertTrue(blackQueen.getColor().equals(Color.BLACK));
              
              
              
           } catch (IllegalPositionException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
          
       }

       @Test
       void legalMoves_0_initial() {
           testBoard.initialize();
          
           try {
               ChessPiece whiteQueen = testBoard.getPiece("d1");
               assertTrue(whiteQueen.legalMoves().size() == 0);
              
           } catch (IllegalPositionException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
          
       }

       @Test
       void testLegalMoves_18() {
       try {
           ChessPiece Queen = new Queen(testBoard, Color.WHITE);
           ChessPiece Queen1 = new Queen(testBoard, Color.WHITE);
           testBoard.placePiece(Queen, "a6");
           testBoard.placePiece(Queen1, "f6");
           int expect = 18;
           int actual = Queen.legalMoves().size();
           assertEquals(expect, actual);
       } catch (Exception e) {
           e.printStackTrace();
       }
    
       }

       @Test
       void testLegalMoves_0_blocked() {
          try {
            ChessPiece Queen = new Queen(testBoard, Color.WHITE);
            ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn2 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn3 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn4 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn5 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn6 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn7 = new Pawn(testBoard, Color.WHITE);
            ChessPiece Pawn8 = new Pawn(testBoard, Color.WHITE);
            testBoard.placePiece(Queen, "f6");
            testBoard.placePiece(Pawn1, "f7");
            testBoard.placePiece(Pawn2, "f5");
            testBoard.placePiece(Pawn3, "e6");
            testBoard.placePiece(Pawn4, "g6");
            testBoard.placePiece(Pawn5, "e5");
            testBoard.placePiece(Pawn6, "e7");
            testBoard.placePiece(Pawn7, "g5");
            testBoard.placePiece(Pawn8, "g7");
            int expect = 0;
            int actual = Queen.legalMoves().size();
            assertEquals(expect, actual);
            } catch (Exception e) {
            e.printStackTrace();
        }
       
    }


    @Test
    void testLegalMoves_blackColor() {
    try {
        ChessPiece Queen = new Queen(testBoard, Color.WHITE);
        ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
        ChessPiece Pawn2 = new Pawn(testBoard, Color.BLACK);
        testBoard.placePiece(Queen, "f6");
        testBoard.placePiece(Pawn1, "e6");
        testBoard.placePiece(Pawn2, "f5");
        int expect = 17;
        int actual = Queen.legalMoves().size();
        assertEquals(expect, actual);
    } catch (Exception e) {
        e.printStackTrace();
    }
   
}

@Test
      void testLegalMoves_SurroundedByOppositeColor() {
         try {
           ChessPiece Queen = new Queen(testBoard, Color.BLACK);
           ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn2 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn3 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn4 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn5 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn6 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn7 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn8 = new Pawn(testBoard, Color.WHITE);
           testBoard.placePiece(Queen, "f6");
           testBoard.placePiece(Pawn1, "f7");
           testBoard.placePiece(Pawn2, "f5");
           testBoard.placePiece(Pawn3, "e6");
           testBoard.placePiece(Pawn4, "g6");
           testBoard.placePiece(Pawn5, "e5");
           testBoard.placePiece(Pawn6, "e7");
           testBoard.placePiece(Pawn7, "g5");
           testBoard.placePiece(Pawn8, "g7");
           int expect = 8;
           int actual = Queen.legalMoves().size();
           assertEquals(expect, actual);
           } catch (Exception e) {
           e.printStackTrace();
       }
      
   }

   @Test
      void testLegalMoves_SurroundedByOppositeColor_testPosition() {
         try {
           ChessPiece Queen = new Queen(testBoard, Color.BLACK);
           ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn2 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn3 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn4 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn5 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn6 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn7 = new Pawn(testBoard, Color.WHITE);
           ChessPiece Pawn8 = new Pawn(testBoard, Color.WHITE);
           testBoard.placePiece(Queen, "f6");
           testBoard.placePiece(Pawn1, "f7");
           testBoard.placePiece(Pawn2, "f5");
           testBoard.placePiece(Pawn3, "e6");
           testBoard.placePiece(Pawn4, "g6");
           testBoard.placePiece(Pawn5, "e5");
           testBoard.placePiece(Pawn6, "e7");
           testBoard.placePiece(Pawn7, "g5");
           testBoard.placePiece(Pawn8, "g7");
           ArrayList<String> expect = new ArrayList<>();
           expect.add("f7");
           expect.add("f5");
           expect.add("e6");
           expect.add("g6");
           expect.add("e5");
           expect.add("e7");
           expect.add("g5");
           expect.add("g7");

           Collections.sort(expect);

           ArrayList<String> actual = Queen.legalMoves();

           Collections.sort(actual);

           assertEquals(expect, actual);
           } catch (Exception e) {
           e.printStackTrace();
       }
      
   }


   @Test
   void testLegalMoves_TestPosition() {
      try {
        ChessPiece Queen = new Queen(testBoard, Color.BLACK);
        ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
        ChessPiece Pawn2 = new Pawn(testBoard, Color.WHITE);
        ChessPiece Pawn3 = new Pawn(testBoard, Color.WHITE);
        testBoard.placePiece(Queen, "f6");
        testBoard.placePiece(Pawn1, "f7");
        testBoard.placePiece(Pawn2, "f5");
        testBoard.placePiece(Pawn3, "e6");
        ArrayList<String> expect = new ArrayList<>();
        Collections.addAll(expect, "f7", "f5", "e6", "a1", "b2", "c3", "d4", "e5", "g7", "h8", "d8", "e7", "g5", "h4 ");
       /* expect.add("f7");
        expect.add("f5");
        expect.add("e6");
        expect.add("g6");
        expect.add("e5");
        expect.add("e7");
        expect.add("g5");
        expect.add("g7"); */

        Collections.sort(expect);

        ArrayList<String> actual = Queen.legalMoves();

        Collections.sort(actual);

        assertEquals(expect, actual);
        } catch (Exception e) {
        e.printStackTrace();
    }
   
}



}


