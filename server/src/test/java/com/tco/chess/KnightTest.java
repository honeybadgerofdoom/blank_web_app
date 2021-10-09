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
	void legalMoves_0() {
		testBoard.initialize();
		ChessPiece pawn1 = new Pawn(testBoard, Color.WHITE);
		ChessPiece pawn2 = new Pawn(testBoard, Color.WHITE);
		testBoard.placePiece(pawn1, "a3");
		testBoard.placePiece(pawn2, "c3");
		try {
			assertTrue(board.getPiece("b1").legalMoves().size() == 0);
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void test_number_legalMoves_from_initialize() {
		testBoard.initialize();
		try {
			Knight knight1 = testBoard.getPiece("b1");
			Knight knight2 = testBoard.getPiece("g1");
			Knight blackKnight1 = testBoard.getPiece("b8");
			Knight blackKnight2 = testBoard.getPiece("g8");

			assertTrue(knight1.legalMoves().size() == 2);
			assertTrue(knight2.legalMoves().size() == 2);
			assertTrue(blackKnight1.legalMoves().size() == 2);
			assertTrue(blackKnight2.legalMoves().size() == 2);
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void test_actual_legalMoves_from_initialize() {
		testBoard.initialize();
		try {
			ArrayList<String> testList = new ArrayList<String>("a3", "c3");
			assertEquals(testBoard.getPiece("b1"), testList, () -> "Knight at b1 has invalid legal move list");
			testList.set(0, "f3");
			testList.set(1, "h3");
			assertEquals(testBoard.getPiece("g1"), testList, () -> "Knight at g1 has invalid legal move list");
			testList.set(0, "a6");
			testList.set(1, "c6");
			assertEquals(testBoard.getPiece("b8"), testList, () -> "Knight at b8 has invalid legal move list");
			testList.set(0, "f6");
			testList.set(1, "h6");
			assertEquals(testBoard.getPiece("g8"), testList, () -> "Knight at g8 has invalid legal move list");
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 

	@Test
	void test_legalMoves_middle_emptyBoard() {
		ArrayList<String> testList = new ArrayList<String>();
		ChessPiece blackKnight = new Knight(testBoard, Color.BLACK);
		testBoard.placePiece(blackKnight, "d5");


		assertTrue(whiteKnight.legalMoves().size() == 8);

	}

	@Test
	void test_legalMoves_not_contain_friendly_pieces() {
		ArrayList<String> testList = new ArrayList<String>();
		ChessPiece blackKnight = new Knight(testBoard, Color.BLACK);
		testBoard.placePiece(blackKnight, "d5");
		ChessPiece blackPawn = new Pawn(testBoard, Color.BLACK);
		testBoard.placePiece(blackPawn, "c3");
		ChessPiece blackKing = new King(testBoard, Color.BLACK);
		testBoard.placePiece(blackKing, "b6");

		assertTrue(whiteKnight.legalMoves().size() == 6);
	}

	@Test
	void test_capturing_enemy() {
		ArrayList<String> testList = new ArrayList<String>();
		ChessPiece whiteKnight = new Knight(testBoard, Color.WHITE);
		testBoard.placePiece(whiteKnight, "d5");
		ChessPiece blackPawn = new Pawn(testBoard, Color.BLACK);
		testBoard.placePiece(blackPawn, "c3");
		ChessPiece blackKing = new King(testBoard, Color.BLACK);
		testBoard.placePiece(blackKing, "b6");

		assertTrue(whiteKnight.legalMoves().size() == 8);
	}

	@Test
	void test_moving_three_times() {
		ChessPiece blackKnight = new Knight(testBoard, Color.BLACK);
		testBoard.placePiece(blackKnight, "a1");
	}
	
}
