package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.tco.chess.ChessPiece.Color;

class KingTest {
	private ChessBoard testBoard;
	
	@BeforeEach
	private void createTestBoard() {
		testBoard = new ChessBoard();
	}

	@Test
	void testColor() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteKing = testBoard.getPiece("e1");
			ChessPiece blackKing = testBoard.getPiece("e8");
			
			assertTrue(whiteKing instanceof King);
			assertTrue(blackKing instanceof King);
			
			assertTrue(whiteKing.getColor().equals(Color.WHITE));
			assertTrue(blackKing.getColor().equals(Color.BLACK));
			
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_0_StartingPosition() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteKing = testBoard.getPiece("e1");
			assertTrue(whiteKing.legalMoves().size() == 0);

		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_8_EmptyBoard() {
		try {
			ChessPiece king = new King(testBoard, Color.WHITE);
			testBoard.placePiece(king, "e5");
			assertTrue(king.legalMoves().size() == 8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_0_surroundedByFriends() {
		try {
			ChessPiece king = new King(testBoard, Color.WHITE);
			ChessPiece king1 = new King(testBoard, Color.WHITE);
			ChessPiece king2 = new King(testBoard, Color.WHITE);
			ChessPiece king3 = new King(testBoard, Color.WHITE);
			ChessPiece king4 = new King(testBoard, Color.WHITE);
			ChessPiece king5 = new King(testBoard, Color.WHITE);
			ChessPiece king6 = new King(testBoard, Color.WHITE);
			ChessPiece king7 = new King(testBoard, Color.WHITE);
			ChessPiece king8 = new King(testBoard, Color.WHITE);
			
			testBoard.placePiece(king, "e5");
			testBoard.placePiece(king1, "e6");
			testBoard.placePiece(king2, "e4");
			testBoard.placePiece(king3, "d5");
			testBoard.placePiece(king4, "f5");
			testBoard.placePiece(king5, "f6");
			testBoard.placePiece(king6, "d6");
			testBoard.placePiece(king7, "f4");
			testBoard.placePiece(king8, "d4");
			assertTrue(king.legalMoves().size() == 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_8_surroundedByEnemies() {
		try {
			ChessPiece king = new King(testBoard, Color.WHITE);
			ChessPiece king1 = new King(testBoard, Color.BLACK);
			ChessPiece king2 = new King(testBoard, Color.BLACK);
			ChessPiece king3 = new King(testBoard, Color.BLACK);
			ChessPiece king4 = new King(testBoard, Color.BLACK);
			ChessPiece king5 = new King(testBoard, Color.BLACK);
			ChessPiece king6 = new King(testBoard, Color.BLACK);
			ChessPiece king7 = new King(testBoard, Color.BLACK);
			ChessPiece king8 = new King(testBoard, Color.BLACK);
			
			testBoard.placePiece(king, "e5");
			testBoard.placePiece(king1, "e6");
			testBoard.placePiece(king2, "e4");
			testBoard.placePiece(king3, "d5");
			testBoard.placePiece(king4, "f5");
			testBoard.placePiece(king5, "f6");
			testBoard.placePiece(king6, "d6");
			testBoard.placePiece(king7, "f4");
			testBoard.placePiece(king8, "d4");
			assertTrue(king.legalMoves().size() == 8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_8_mostlySurrouned() {
		try {
			ChessPiece king = new King(testBoard, Color.WHITE);
			ChessPiece king1 = new King(testBoard, Color.BLACK);
			ChessPiece king2 = new King(testBoard, Color.BLACK);
			ChessPiece king3 = new King(testBoard, Color.BLACK);
			ChessPiece king4 = new King(testBoard, Color.BLACK);
			ChessPiece king5 = new King(testBoard, Color.BLACK);
			ChessPiece king6 = new King(testBoard, Color.BLACK);
		
			
			testBoard.placePiece(king, "e5");
			testBoard.placePiece(king1, "e6");
			testBoard.placePiece(king2, "e4");
			testBoard.placePiece(king3, "d5");
			testBoard.placePiece(king4, "f5");
			testBoard.placePiece(king5, "f6");
			testBoard.placePiece(king6, "d6");
			
			assertTrue(king.legalMoves().size() == 8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_2_mostlySurrouned_F() {
		try {
			ChessPiece king = new King(testBoard, Color.WHITE);
			ChessPiece king1 = new King(testBoard, Color.WHITE);
			ChessPiece king2 = new King(testBoard, Color.WHITE);
			ChessPiece king3 = new King(testBoard, Color.WHITE);
			ChessPiece king4 = new King(testBoard, Color.WHITE);
			ChessPiece king5 = new King(testBoard, Color.WHITE);
			ChessPiece king6 = new King(testBoard, Color.WHITE);
		
			
			testBoard.placePiece(king, "e5");
			testBoard.placePiece(king1, "e6");
			testBoard.placePiece(king2, "e4");
			testBoard.placePiece(king3, "d5");
			testBoard.placePiece(king4, "f5");
			testBoard.placePiece(king5, "f6");
			testBoard.placePiece(king6, "d6");
			
			assertTrue(king.legalMoves().size() == 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMovesContains() {
		try {
			ChessPiece king = new King(testBoard, Color.WHITE);
			ChessPiece king1 = new King(testBoard, Color.WHITE);
			ChessPiece king2 = new King(testBoard, Color.WHITE);
			ChessPiece king3 = new King(testBoard, Color.WHITE);
			ChessPiece king4 = new King(testBoard, Color.WHITE);
			ChessPiece king5 = new King(testBoard, Color.WHITE);
			ChessPiece king6 = new King(testBoard, Color.WHITE);
		
			
			testBoard.placePiece(king, "e5");
			testBoard.placePiece(king1, "e6");
			testBoard.placePiece(king2, "e4");
			testBoard.placePiece(king3, "d5");
			testBoard.placePiece(king4, "f5");
			testBoard.placePiece(king5, "f6");
			testBoard.placePiece(king6, "d6");
			
			assertTrue(king.legalMoves().contains("d4"));
			assertTrue(king.legalMoves().contains("f4"));
			assertTrue(king.legalMoves().size() == 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void whiteKingCanCastleQueenside() {
		testBoard.initialize();
		try {
			testBoard.placePiece(testBoard.getPiece("b1"), "b4");
			testBoard.placePiece(testBoard.getPiece("c1"), "c4");
			testBoard.placePiece(testBoard.getPiece("d1"), "d4");
			ChessPiece whiteKing = testBoard.getPiece("e1");
			ArrayList<String> legalMoves = whiteKing.legalMoves();
			assertEquals(2, legalMoves.size());
			ArrayList<String> validMoves = new ArrayList<String>();
			validMoves.add("c1");
			validMoves.add("d1");
			for (String move : legalMoves) {
				assertTrue(validMoves.contains(move));
			}
		} catch (IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void whiteKingCanCastleKingside() {
		testBoard.initialize();
		try {
			testBoard.placePiece(testBoard.getPiece("f1"), "f4");
			testBoard.placePiece(testBoard.getPiece("g1"), "g4");
			ChessPiece whiteKing = testBoard.getPiece("e1");
			ArrayList<String> legalMoves = whiteKing.legalMoves();
			assertEquals(2, legalMoves.size());
			ArrayList<String> validMoves = new ArrayList<String>();
			validMoves.add("f1");
			validMoves.add("g1");
			for (String move : legalMoves) {
				assertTrue(validMoves.contains(move));
			}
		} catch (IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void blackKingCanCastleQueenside() {
		testBoard.initialize();
		try {
			testBoard.placePiece(testBoard.getPiece("b8"), "b5");
			testBoard.placePiece(testBoard.getPiece("c8"), "c5");
			testBoard.placePiece(testBoard.getPiece("d8"), "d5");
			ChessPiece whiteKing = testBoard.getPiece("e8");
			ArrayList<String> legalMoves = whiteKing.legalMoves();
			assertEquals(2, legalMoves.size());
			ArrayList<String> validMoves = new ArrayList<String>();
			validMoves.add("c8");
			validMoves.add("d8");
			for (String move : legalMoves) {
				assertTrue(validMoves.contains(move));
			}
		} catch (IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void blackKingCanCastleKingside() {
		testBoard.initialize();
		try {
			testBoard.placePiece(testBoard.getPiece("f8"), "f5");
			testBoard.placePiece(testBoard.getPiece("g8"), "g5");
			ChessPiece whiteKing = testBoard.getPiece("e8");
			ArrayList<String> legalMoves = whiteKing.legalMoves();
			assertEquals(2, legalMoves.size());
			ArrayList<String> validMoves = new ArrayList<String>();
			validMoves.add("f8");
			validMoves.add("g8");
			for (String move : legalMoves) {
				assertTrue(validMoves.contains(move));
			}
		} catch (IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void kingCantCastleAfterMoving() {
		testBoard.initialize();
		try {
			testBoard.placePiece(testBoard.getPiece("f8"), "f5");
			testBoard.placePiece(testBoard.getPiece("g8"), "g5");
			testBoard.move("e8", "f8");
			testBoard.move("f8", "e8");
			ChessPiece whiteKing = testBoard.getPiece("e8");
			ArrayList<String> legalMoves = whiteKing.legalMoves();
			assertEquals(1, legalMoves.size());
			assertTrue(legalMoves.contains("f8"));
		} catch (IllegalPositionException | IllegalMoveException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void kingCantCastleAfterRookMoves() {
		testBoard.initialize();
		try {
			ChessPiece whiteKing = testBoard.getPiece("e1");
			testBoard.placePiece(testBoard.getPiece("b1"), "b3");
			testBoard.placePiece(testBoard.getPiece("c1"), "c3");
			testBoard.placePiece(testBoard.getPiece("d1"), "d3");
			testBoard.move("a1", "b1");
			assertEquals(1, whiteKing.legalMoves().size());
			assertTrue(whiteKing.legalMoves().contains("d1"));
			testBoard.move("b1", "a1");
			assertEquals(1, whiteKing.legalMoves().size());
			assertTrue(whiteKing.legalMoves().contains("d1"));
		} catch (IllegalMoveException | IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
	}

}
