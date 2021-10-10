package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.util.*;
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
			assertTrue(testBoard.getPiece("b1").legalMoves().size() == 0);
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void test_number_legalMoves_from_initialize() {
		testBoard.initialize();
		try {
			ChessPiece knight1 = testBoard.getPiece("b1");
			ChessPiece knight2 = testBoard.getPiece("g1");
			ChessPiece blackKnight1 = testBoard.getPiece("b8");
			ChessPiece blackKnight2 = testBoard.getPiece("g8");

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
			assertTrue(testBoard.getPiece("b1").legalMoves().contains("a3") && testBoard.getPiece("b1").legalMoves().contains("c3"));
			assertTrue(testBoard.getPiece("g1").legalMoves().contains("f3") && testBoard.getPiece("g1").legalMoves().contains("h3"));
			assertTrue(testBoard.getPiece("b8").legalMoves().contains("a6") && testBoard.getPiece("b8").legalMoves().contains("c6"));
			assertTrue(testBoard.getPiece("g8").legalMoves().contains("f6") && testBoard.getPiece("g8").legalMoves().contains("h6"));
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


		assertTrue(blackKnight.legalMoves().size() == 8);

	}

	@Test
	void test_legalMoves_not_contain_friendly_pieces() {
		ChessPiece blackKnight = new Knight(testBoard, Color.BLACK);
		testBoard.placePiece(blackKnight, "d5");
		ChessPiece blackPawn = new Pawn(testBoard, Color.BLACK);
		testBoard.placePiece(blackPawn, "c3");
		ChessPiece blackKing = new King(testBoard, Color.BLACK);
		testBoard.placePiece(blackKing, "b6");

		ArrayList<String> testList = new ArrayList<String>(Arrays.asList("b4","c7", "e3", "e7", "f4", "f6"));
		assertTrue(blackKnight.legalMoves().size() == testList.size());

		for (int i = 0; i < testList.size(); i++) {
			assertTrue(blackKnight.legalMoves().contains(testList.get(i)));
		}
	}

	@Test
	void test_capturing_enemy() {
		ChessPiece whiteKnight = new Knight(testBoard, Color.WHITE);
		testBoard.placePiece(whiteKnight, "d5");
		ChessPiece blackPawn = new Pawn(testBoard, Color.BLACK);
		testBoard.placePiece(blackPawn, "c3");
		ChessPiece blackKing = new King(testBoard, Color.BLACK);
		testBoard.placePiece(blackKing, "b6");

		ArrayList<String> testList = new ArrayList<String>(Arrays.asList("b4","c7", "e3", "e7", "f4", "f6", "c3", "b6"));
		assertTrue(whiteKnight.legalMoves().size() == testList.size());

		for (int i = 0; i < testList.size(); i++) {
			assertTrue(whiteKnight.legalMoves().contains(testList.get(i)));
		}
	}

	@Test
	void test_moving_three_times() {
		try {
			ChessPiece blackKnight = new Knight(testBoard, Color.BLACK);
			testBoard.placePiece(blackKnight, "a1");
			assertTrue(blackKnight.legalMoves().size() == 2);
			testBoard.move("a1", "b3");
			assertTrue(blackKnight.legalMoves().size() == 6);
			testBoard.move("b3", "d4");
			ArrayList<String> testList = new ArrayList<String>(Arrays.asList("b5", "b3", "c6", "e6", "f5", "f3", "e2", "c2"));
			assertTrue(blackKnight.legalMoves().size() == testList.size());
			for (int i = 0; i < testList.size(); i++) {
				assertTrue(blackKnight.legalMoves().contains(testList.get(i)));
			}
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
	}
	
}
