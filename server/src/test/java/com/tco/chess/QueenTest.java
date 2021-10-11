package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		void testLegalMoves_12() {
		try {
			ChessPiece Queen = new Queen(testBoard, Color.WHITE);
			ChessPiece Queen1 = new Bishop(testBoard, Color.WHITE);
			testBoard.placePiece(Queen, "a6");
			testBoard.placePiece(Queen1, "f6");

			int expect = 18;
			int actual = Queen.legalMoves().size();
			assertEquals(expect, actual);


			//assertTrue(Queen.legalMoves().size() == 28);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
		/*@Test
		void legalMoves_0() {
			ChessPiece whiteQueen = new Queen(testBoard, Color.BLACK);
			testBoard.placePiece(whiteQueen, "f6");
			assertTrue(whiteQueen.legalMoves().size() == 0);
			
		} */
		
}
