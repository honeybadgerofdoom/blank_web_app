package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;

class KnightTest {
	private ChessBoard testBoard;
	
	@BeforeEach
	private void createTestBoard() {
		testBoard = new ChessBoard();
	}

	@Test
	void testColor_InitialPosition() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteKnight = testBoard.getPiece("g1");
			ChessPiece blackKnight = testBoard.getPiece("b8");
			ChessPiece whiteKnight1 = testBoard.getPiece("b1");
			ChessPiece blackKnight1 = testBoard.getPiece("g8");
			
			
			assertTrue(whiteKnight instanceof Knight);
			assertTrue(blackKnight instanceof Knight);
			assertTrue(whiteKnight1 instanceof Knight);
			assertTrue(blackKnight1 instanceof Knight);
			
			assertTrue(whiteKnight.getColor().equals(Color.WHITE));
			assertTrue(blackKnight.getColor().equals(Color.BLACK));
			assertTrue(whiteKnight1.getColor().equals(Color.WHITE));
			assertTrue(blackKnight1.getColor().equals(Color.BLACK));
			
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void legalMoves_0_initial() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteKnight = testBoard.getPiece("b1");
			assertTrue(whiteKnight.legalMoves().size() == 0);
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void legalMoves_0() {
		ChessPiece whiteKnight = new Knight(testBoard, Color.BLACK);
		testBoard.placePiece(whiteKnight, "f6");
		assertTrue(whiteKnight.legalMoves().size() == 0);
		
	}

	@Test
	void test_number_legalMoves_from_initialize() {
		testBoard.initialize();

		Knight knight1 = testBoard.getPiece("b1");
		Knight knight2 = testBoard.getPiece("g1");
		Knight blackKnight1 = testBoard.getPiece("b8");
		Knight blackKnight2 = testBoard.getPiece("g8");

		assertTrue(knight1.legalMoves().size() == 2);
		assertTrue(knight2.legalMoves().size() == 2):
		assertTrue(blackKnight1.legalMoves().size() == 2);
		assertTrue(blackKnight2.legalMoves().size() == 2);
	}
	
	@Test
	void test_actual_legalMoves_from_initialize() {
		testBoard.initialize();
		ArrayList<String> testList = new ArrayList<String>;
		

	} 
	
}
