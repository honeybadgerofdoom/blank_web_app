package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;

class BishopTest {
	private ChessBoard testBoard;
	
	@BeforeEach
	private void createTestBoard() {
		testBoard = new ChessBoard();
	}
	
	@Test
	void testColor() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteBishop = testBoard.getPiece("c1");
			ChessPiece blackBishop = testBoard.getPiece("c8");
			
			assertTrue(whiteBishop instanceof Bishop);
			assertTrue(blackBishop instanceof Bishop);
			
			assertTrue(whiteBishop.getColor().equals(Color.WHITE));
			assertTrue(blackBishop.getColor().equals(Color.BLACK));
			
			ChessPiece whiteBishopR = testBoard.getPiece("f1");
			ChessPiece blackBishopR = testBoard.getPiece("f8");
			
			assertTrue(whiteBishopR instanceof Bishop);
			assertTrue(blackBishopR instanceof Bishop);
			
			assertTrue(whiteBishopR.getColor().equals(Color.WHITE));
			assertTrue(blackBishopR.getColor().equals(Color.BLACK));
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_0() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteRook = testBoard.getPiece("a1");
			assertTrue(whiteRook.legalMoves().size() == 0);

		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_10() {
		try {
			ChessPiece Bishop = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop1 = new Bishop(testBoard, Color.WHITE);
			testBoard.placePiece(Bishop, "e5");
			testBoard.placePiece(Bishop1, "f6");
			assertTrue(Bishop.legalMoves().size() == 10);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_11() {
		try {
			ChessPiece Bishop = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop1 = new Bishop(testBoard, Color.BLACK);
			testBoard.placePiece(Bishop, "e5");
			testBoard.placePiece(Bishop1, "f6");
			assertTrue(Bishop.legalMoves().size() == 11);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_13() {
		try {
			ChessPiece Bishop = new Bishop(testBoard, Color.WHITE);
			testBoard.placePiece(Bishop, "e5");
			assertTrue(Bishop.legalMoves().size() == 13);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_4() {
		try {
			ChessPiece Bishop = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop1 = new Bishop(testBoard, Color.BLACK);
			ChessPiece Bishop2= new Bishop(testBoard, Color.BLACK);
			ChessPiece Bishop3 = new Bishop(testBoard, Color.BLACK);
			ChessPiece Bishop4 = new Bishop(testBoard, Color.BLACK);
			testBoard.placePiece(Bishop, "e5");
			testBoard.placePiece(Bishop1, "f6");
			testBoard.placePiece(Bishop2, "f4");
			testBoard.placePiece(Bishop3, "d4");
			testBoard.placePiece(Bishop4, "d6");
			assertTrue(Bishop.legalMoves().size() == 4);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_0_withoutEdge() {
		try {
			ChessPiece Bishop = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop1 = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop2= new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop3 = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop4 = new Bishop(testBoard, Color.WHITE);
			testBoard.placePiece(Bishop, "e5");
			testBoard.placePiece(Bishop1, "f6");
			testBoard.placePiece(Bishop2, "f4");
			testBoard.placePiece(Bishop3, "d4");
			testBoard.placePiece(Bishop4, "d6");
			assertTrue(Bishop.legalMoves().size() == 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMovesContains() {
		try {
			ChessPiece Bishop = new Bishop(testBoard, Color.WHITE);
			ChessPiece Bishop1 = new Bishop(testBoard, Color.BLACK);
			ChessPiece Bishop2= new Bishop(testBoard, Color.BLACK);
			ChessPiece Bishop3 = new Bishop(testBoard, Color.BLACK);
			ChessPiece Bishop4 = new Bishop(testBoard, Color.BLACK);
			testBoard.placePiece(Bishop, "e6");
			testBoard.placePiece(Bishop1, "f7");
			testBoard.placePiece(Bishop2, "f5");
			testBoard.placePiece(Bishop3, "d5");
			testBoard.placePiece(Bishop4, "d7");
			
			assertTrue(Bishop.legalMoves().contains("f7"));
			assertTrue(Bishop.legalMoves().contains("f5"));
			assertTrue(Bishop.legalMoves().contains("d5"));
			assertTrue(Bishop.legalMoves().contains("d7"));
			assertTrue(Bishop.legalMoves().size() == 4);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
