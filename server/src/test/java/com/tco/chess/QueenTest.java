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
				
				System.out.println("Testidslfjdslf");
				
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/*
		@Test
		void legalMoves_14_initial() {
			testBoard.initialize();
			
			try {
				ChessPiece whiteQueen = testBoard.getPiece("d1");

				//testing 
				System.out.println("Queen size:   " + whiteQueen.legalMoves().size() + "\n\n\n");
				//assertTrue(whiteQueen.legalMoves().size() == 14);
				
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
		/*@Test
		void legalMoves_0() {
			ChessPiece whiteQueen = new Queen(testBoard, Color.BLACK);
			testBoard.placePiece(whiteQueen, "f6");
			assertTrue(whiteQueen.legalMoves().size() == 0);
			
		} */
		
}
