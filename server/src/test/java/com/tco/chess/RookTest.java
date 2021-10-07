package a2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import a2.ChessPiece.Color;

class RookTest {
	private ChessBoard testBoard;
	
	@BeforeEach
	private void createTestBoard() {
		testBoard = new ChessBoard();
	}
	
	@Test
	void testColor() {
		testBoard.initialize();
		
		try {
			ChessPiece whiteRook = testBoard.getPiece("a1");
			ChessPiece blackRook = testBoard.getPiece("a8");
			
			assertTrue(whiteRook instanceof Rook);
			assertTrue(blackRook instanceof Rook);
			
			assertTrue(whiteRook.getColor().equals(Color.WHITE));
			assertTrue(blackRook.getColor().equals(Color.BLACK));
			
			ChessPiece whiteRookR = testBoard.getPiece("h1");
			ChessPiece blackRookR = testBoard.getPiece("h8");
			
			assertTrue(whiteRookR instanceof Rook);
			assertTrue(blackRookR instanceof Rook);
			
			assertTrue(whiteRookR.getColor().equals(Color.WHITE));
			assertTrue(blackRookR.getColor().equals(Color.BLACK));
			
			
			
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
	void testLegalMoves_12() {
		try {
			ChessPiece rook = new Rook(testBoard, Color.WHITE);
			ChessPiece rook1 = new Rook(testBoard, Color.BLACK);
			testBoard.placePiece(rook, "e5");
			testBoard.placePiece(rook1, "f5");
			assertTrue(rook.legalMoves().size() == 12);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_14() {
		try {
			ChessPiece rook = new Rook(testBoard, Color.WHITE);
			testBoard.placePiece(rook, "e5");
			assertTrue(rook.legalMoves().size() == 14);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_11() {
		try {
			ChessPiece rook = new Rook(testBoard, Color.WHITE);
			ChessPiece rook1 = new Rook(testBoard, Color.WHITE);
			testBoard.placePiece(rook, "e5");
			testBoard.placePiece(rook1, "e6");
			assertTrue(rook.legalMoves().size() == 11);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_0_withoutEdge() {
		try {
			ChessPiece rook = new Rook(testBoard, Color.WHITE);
			ChessPiece rook1 = new Rook(testBoard, Color.WHITE);
			ChessPiece rook2 = new Rook(testBoard, Color.WHITE);
			ChessPiece rook3 = new Rook(testBoard, Color.WHITE);
			ChessPiece rook4 = new Rook(testBoard, Color.WHITE);
			testBoard.placePiece(rook, "e5");
			testBoard.placePiece(rook1, "e6");
			testBoard.placePiece(rook2, "e4");
			testBoard.placePiece(rook3, "d5");
			testBoard.placePiece(rook4, "f5");
			
			assertTrue(rook.legalMoves().size() == 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMoves_4_withoutEdge() {
		try {
			ChessPiece rook = new Rook(testBoard, Color.WHITE);
			ChessPiece rook1 = new Rook(testBoard, Color.BLACK);
			ChessPiece rook2 = new Rook(testBoard, Color.BLACK);
			ChessPiece rook3 = new Rook(testBoard, Color.BLACK);
			ChessPiece rook4 = new Rook(testBoard, Color.BLACK);
			testBoard.placePiece(rook, "e5");
			testBoard.placePiece(rook1, "e6");
			testBoard.placePiece(rook2, "e4");
			testBoard.placePiece(rook3, "d5");
			testBoard.placePiece(rook4, "f5");
			
			assertTrue(rook.legalMoves().size() == 4);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLegalMovesConains() {
		try {
			ChessPiece rook = new Rook(testBoard, Color.WHITE);
			ChessPiece rook1 = new Rook(testBoard, Color.BLACK);
			ChessPiece rook2 = new Rook(testBoard, Color.BLACK);
			ChessPiece rook3 = new Rook(testBoard, Color.BLACK);
			ChessPiece rook4 = new Rook(testBoard, Color.BLACK);
			testBoard.placePiece(rook, "e5");
			testBoard.placePiece(rook1, "e6");
			testBoard.placePiece(rook2, "e4");
			testBoard.placePiece(rook3, "d5");
			testBoard.placePiece(rook4, "f5");
			
			assertTrue(rook.legalMoves().contains("e6"));
			assertTrue(rook.legalMoves().contains("e4"));
			assertTrue(rook.legalMoves().contains("d5"));
			assertTrue(rook.legalMoves().contains("f5"));
			assertTrue(rook.legalMoves().size() == 4);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
