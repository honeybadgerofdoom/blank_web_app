package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;

class PawnTest {
	private ChessBoard testBoard;
	
	@BeforeEach
	private void createTestBoard() {
		testBoard = new ChessBoard();
	}
	
	@Test
	void testColor() {
		testBoard.initialize();
		
		try {
			ChessPiece Pawn = testBoard.getPiece("a2");
			ChessPiece Pawn1 = testBoard.getPiece("b2");
			ChessPiece Pawn2 = testBoard.getPiece("c2");
			ChessPiece Pawn3 = testBoard.getPiece("d2");
			ChessPiece Pawn4 = testBoard.getPiece("e2");
			ChessPiece Pawn5 = testBoard.getPiece("f2");
			ChessPiece Pawn6 = testBoard.getPiece("g2");
			ChessPiece Pawn7 = testBoard.getPiece("h2");
			
			ChessPiece Pawn8 = testBoard.getPiece("a7");
			ChessPiece Pawn9 = testBoard.getPiece("b7");
			ChessPiece Pawn10 = testBoard.getPiece("c7");
			ChessPiece Pawn11 = testBoard.getPiece("d7");
			ChessPiece Pawn12 = testBoard.getPiece("e7");
			ChessPiece Pawn13 = testBoard.getPiece("f7");
			ChessPiece Pawn14 = testBoard.getPiece("g7");
			ChessPiece Pawn15 = testBoard.getPiece("h7");
			
			
			
			assertTrue(Pawn instanceof Pawn);
			assertTrue(Pawn1 instanceof Pawn);
			assertTrue(Pawn2 instanceof Pawn);
			assertTrue(Pawn3 instanceof Pawn);
			assertTrue(Pawn4 instanceof Pawn);
			assertTrue(Pawn5 instanceof Pawn);
			assertTrue(Pawn6 instanceof Pawn);
			assertTrue(Pawn7 instanceof Pawn);
			assertTrue(Pawn8 instanceof Pawn);
			assertTrue(Pawn9 instanceof Pawn);
			assertTrue(Pawn10 instanceof Pawn);
			assertTrue(Pawn11 instanceof Pawn);
			assertTrue(Pawn12 instanceof Pawn);
			assertTrue(Pawn13 instanceof Pawn);
			assertTrue(Pawn14 instanceof Pawn);
			assertTrue(Pawn15 instanceof Pawn);
			
			
			assertTrue(Pawn.getColor().equals(Color.WHITE));
			assertTrue(Pawn1.getColor().equals(Color.WHITE));
			assertTrue(Pawn2.getColor().equals(Color.WHITE));
			assertTrue(Pawn3.getColor().equals(Color.WHITE));
			assertTrue(Pawn4.getColor().equals(Color.WHITE));
			assertTrue(Pawn5.getColor().equals(Color.WHITE));
			assertTrue(Pawn6.getColor().equals(Color.WHITE));
			assertTrue(Pawn7.getColor().equals(Color.WHITE));
			
			
			assertTrue(Pawn8.getColor().equals(Color.BLACK));
			assertTrue(Pawn9.getColor().equals(Color.BLACK));
			assertTrue(Pawn10.getColor().equals(Color.BLACK));
			assertTrue(Pawn11.getColor().equals(Color.BLACK));
			assertTrue(Pawn12.getColor().equals(Color.BLACK));
			assertTrue(Pawn13.getColor().equals(Color.BLACK));
			assertTrue(Pawn14.getColor().equals(Color.BLACK));
			assertTrue(Pawn15.getColor().equals(Color.BLACK));
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testLegalMoves_2() {
		testBoard.initialize();
		
		try {
			ChessPiece whitePawn = testBoard.getPiece("a2");

			assertTrue(whitePawn.legalMoves().size() == 2);

		} catch (IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testLegalMoves_1() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			testBoard.placePiece(Pawn, "e5");
			assertTrue(Pawn.legalMoves().size() == 1);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	@Test
	void hasNoLegalMovesBlockedByPieceWhite() throws IllegalMoveException {
		testBoard.initialize();
		testBoard.move("f7", "f5");
		testBoard.move("f5", "f4");
		testBoard.move("f4", "f3");
		assertThrows(IllegalMoveException.class, () -> testBoard.move("f2", "f4"));
	}

	@Test
	void hasNoLegalMovesBlockedByPieceBlack() throws IllegalMoveException {
		testBoard.initialize();
		testBoard.move("f2", "f4");
		testBoard.move("f4", "f5");
		testBoard.move("f5", "f6");
		assertThrows(IllegalMoveException.class, () -> testBoard.move("f7", "f5"));
	}

	@Test
	void hasNoLegalMovesBlocked() throws IllegalMoveException {
		try {
			testBoard.initialize();
			testBoard.move("f2", "f4");
			testBoard.move("f4", "f5");
			testBoard.move("f5", "f6");
			assertTrue(testBoard.getPiece("f7").legalMoves().size() == 0);
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testBlackDec() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			testBoard.placePiece(Pawn, "e5");
			testBoard.move("e5", "e4");
			assertTrue(Pawn.legalMoves().size() == 1);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testBlackAssertThrowsWhenUp() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.placePiece(Pawn, "e5");
				testBoard.move("e5", "e6");
			});

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testWhiteIncAssertThrowsWhenDown() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.placePiece(Pawn, "e5");
				testBoard.move("e5", "e4");
			});

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testWhiteInc() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			testBoard.placePiece(Pawn, "e5");
			testBoard.move("e5", "e6");
			assertEquals(Pawn.legalMoves().size(), 1);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testWhiteInc2() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			testBoard.placePiece(Pawn, "e2");
			testBoard.move("e2", "e4");
			assertTrue(Pawn.legalMoves().size() == 1);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testWhiteLegalMovesEnemy() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn2 = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(Pawn, "e2");
			testBoard.placePiece(Pawn1, "d3");
			testBoard.placePiece(Pawn2, "f3");
		
			assertEquals(4, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testWhiteLegalMovesEnemy_1() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn2 = new Pawn(testBoard, Color.BLACK);

		
			testBoard.placePiece(Pawn, "e3");
			testBoard.placePiece(Pawn1, "d4");
			testBoard.placePiece(Pawn2, "f4");
		
			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testLegalMovesEnemy_1Not_Starting() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
		
			testBoard.placePiece(Pawn, "e2");
			testBoard.placePiece(Pawn1, "d3");

		
			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testLegalMovesEnemy_2Not_Starting() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
		
			testBoard.placePiece(Pawn, "e4");
			testBoard.placePiece(Pawn1, "d5");

		
			assertEquals(2, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testBLACKLegalMovesEnemy_2Not_Starting() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
		
			testBoard.placePiece(Pawn, "e5");
			testBoard.placePiece(Pawn1, "d4");

		
			assertEquals(1, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testBLACKLegalMovesEnemy() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
		
			testBoard.placePiece(Pawn, "e5");
			testBoard.placePiece(Pawn1, "d4");

		
			assertEquals(2, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testBOUNDS() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
		
			testBoard.placePiece(Pawn, "h5");
			testBoard.placePiece(Pawn1, "h4");
			
			assertEquals(0, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testMOVE_0() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
		
			testBoard.placePiece(Pawn, "h5");
			testBoard.placePiece(Pawn1, "h6");
			
			assertEquals(0, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testMOVE_1() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn2 = new Pawn(testBoard, Color.BLACK);
			
			testBoard.placePiece(Pawn, "h5");
			testBoard.placePiece(Pawn1, "h6");
			testBoard.placePiece(Pawn2, "g6");
			
			assertEquals(1, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testMOVE_2() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn2 = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn3 = new Pawn(testBoard, Color.BLACK);
			
			testBoard.placePiece(Pawn, "e5");
			testBoard.placePiece(Pawn1, "e6");
			testBoard.placePiece(Pawn2, "d6");
			testBoard.placePiece(Pawn3, "f6");
			
			assertEquals(2, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testUPPERBOUNDwhite() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(Pawn, "f8");

			assertEquals(0, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	void testUPPERBOUNDblack() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(Pawn, "f8");

			assertEquals(1, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	void testLowerBOUNDblack() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(Pawn, "f1");

			assertEquals(0, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	void testLowerBOUNDwhite() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(Pawn, "f1");

			assertEquals(1, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	void testEdgeLeft() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
			testBoard.placePiece(Pawn, "a2");
			testBoard.placePiece(Pawn1, "b3");
			
			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testEdgeRight() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
			testBoard.placePiece(Pawn, "h2");
			testBoard.placePiece(Pawn1, "g3");
			
			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	
	@Test
	void testEdgeRightBlack() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
			testBoard.placePiece(Pawn, "h7");
			testBoard.placePiece(Pawn1, "g6");
			
			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testEdgeLeftBlack() {
		try {
			ChessPiece Pawn = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.WHITE);
			testBoard.placePiece(Pawn, "a7");
			testBoard.placePiece(Pawn1, "b6");
			
			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testLegalMovesContains() {
		try {
			ChessPiece Pawn2 = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn1 = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn3 = new Pawn(testBoard, Color.BLACK);
			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(Pawn, "c7");
			testBoard.placePiece(Pawn1, "b8");
			testBoard.placePiece(Pawn2, "d8");
			testBoard.placePiece(Pawn3, "b6");

			assertTrue(Pawn.legalMoves().contains("b8"));
			assertTrue(Pawn.legalMoves().contains("c8"));
			assertTrue(Pawn.legalMoves().contains("d8"));

			assertEquals(3, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	void testLegalMovesContainsWithoutEnemies() {
		try {

			ChessPiece Pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(Pawn, "c7");

			assertTrue(Pawn.legalMoves().contains("c8"));

			assertEquals(1, Pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void enPassantTestL() {
		try {

			ChessPiece pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn, "g5");
			assertTrue(pawn.legalMoves().contains("g6"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn1, "f7");
			testBoard.move("f7", "f5");
			assertTrue(pawn.legalMoves().contains("f6"));
			assertTrue(pawn.legalMoves().contains("g6"));
			assertEquals(2, pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}


	@Test
	void enPassantTestR() {
		try {

			ChessPiece pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn, "g5");
			assertTrue(pawn.legalMoves().contains("g6"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn1, "h7");
			testBoard.move("h7", "h5");
			assertTrue(pawn.legalMoves().contains("h6"));
			assertTrue(pawn.legalMoves().contains("g6"));
			assertEquals(2, pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void enPassantTestLBlack() {
		try {

			ChessPiece pawn = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn, "g4");
			assertTrue(pawn.legalMoves().contains("g3"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn1, "f2");
			testBoard.move("f2", "f4");
			assertTrue(pawn.legalMoves().contains("f3"));
			assertTrue(pawn.legalMoves().contains("g3"));
			assertEquals(2, pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void enPassantTestRBlack() {
		try {

			ChessPiece pawn = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn, "g4");
			assertTrue(pawn.legalMoves().contains("g3"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn1, "h2");
			testBoard.move("h2", "h4");
			assertTrue(pawn.legalMoves().contains("h3"));
			assertTrue(pawn.legalMoves().contains("g3"));
			assertEquals(2, pawn.legalMoves().size());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void enPassantCaptureBlack() {
		try {
			ChessPiece pawn = new Pawn(testBoard, Color.BLACK);
			testBoard.placePiece(pawn, "g4");
			assertTrue(pawn.legalMoves().contains("g3"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn1, "h2");
			testBoard.move("h2", "h4");
			assertTrue(pawn.legalMoves().contains("h3"));
			assertTrue(pawn.legalMoves().contains("g3"));
			assertEquals(2, pawn.legalMoves().size());
			testBoard.move("g4","h3");
			assertNull(testBoard.getPiece("g4"));
			assertNull(testBoard.getPiece("h4"));



		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void enPassantTestWhite() {
		try {

			ChessPiece pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn, "g5");
			assertTrue(pawn.legalMoves().contains("g6"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn1, "h7");
			testBoard.move("h7", "h5");
			assertTrue(pawn.legalMoves().contains("h6"));
			assertTrue(pawn.legalMoves().contains("g6"));
			assertEquals(2, pawn.legalMoves().size());
			testBoard.move("g5","h6");
			assertNull(testBoard.getPiece("g5"));
			assertNull(testBoard.getPiece("h5"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void enPassantTestBlack() {
		try {
			ChessPiece pawn = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn, "e4");
			assertTrue(pawn.legalMoves().contains("e3"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn1, "d2");
			testBoard.move("d2", "d4");
			assertTrue(pawn.legalMoves().contains("d3"));
			assertTrue(pawn.legalMoves().contains("e3"));
			assertEquals(2, pawn.legalMoves().size());
			testBoard.move("e4","d3");
			assertNull(testBoard.getPiece("e4"));
			assertNull(testBoard.getPiece("d4"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void enPassantTestBlackTestEdges() {
		try {
			ChessPiece pawn = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn, "b4");
			assertTrue(pawn.legalMoves().contains("b3"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn1, "a2");
			testBoard.move("a2", "a4");
			assertTrue(pawn.legalMoves().contains("a3"));
			assertTrue(pawn.legalMoves().contains("b3"));
			assertEquals(2, pawn.legalMoves().size());
			testBoard.move("b4","a3");
			assertNull(testBoard.getPiece("b4"));
			assertNull(testBoard.getPiece("a4"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	@Test
	void enPassantTestWhiteTestEdges() {
		try {
			ChessPiece pawn = new Pawn(testBoard, Color.WHITE);

			testBoard.placePiece(pawn, "h5");
			assertTrue(pawn.legalMoves().contains("h6"));
			assertEquals(1, pawn.legalMoves().size());

			ChessPiece pawn1 = new Pawn(testBoard, Color.BLACK);

			testBoard.placePiece(pawn1, "g7");
			testBoard.move("g7", "g5");
			assertTrue(pawn.legalMoves().contains("g6"));
			assertTrue(pawn.legalMoves().contains("h6"));
			assertEquals(2, pawn.legalMoves().size());
			testBoard.move("h5", "g6");
			assertNull(testBoard.getPiece("g5"));
			assertNull(testBoard.getPiece("h5"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	@Test
	void enPassantInitializeWhiteCanCapture2L() {
		try {
			testBoard.initialize();
			testBoard.move("e2","e4");
			testBoard.move("e4","e5");
			testBoard.move("d7","d5");
			testBoard.move("g7","g5");
			testBoard.move("e5","d6");
			assertNull(testBoard.getPiece("d5"));
			assertNull(testBoard.getPiece("e5"));


		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void enPassantInitializeWhiteCanCapture2R() {
		try {
			testBoard.initialize();
			testBoard.move("e2","e4");
			testBoard.move("e4","e5");
			testBoard.move("d7","d5");
			testBoard.move("f7","f5");
			testBoard.move("e5","f6");
			assertNull(testBoard.getPiece("f5"));
			assertNull(testBoard.getPiece("e5"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	void enPassantInitializeBlackCanCapture2L() {
		try {
			testBoard.initialize();
			testBoard.move("c7","c5");
			testBoard.move("c5","c4");
			testBoard.move("d2","d4");
			testBoard.move("b2","b4");
			testBoard.move("c4","b3");
			assertNull(testBoard.getPiece("b4"));
			assertNull(testBoard.getPiece("c4"));


		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void enPassantInitializeBlackCanCapture2R() {
		try {
			testBoard.initialize();
			testBoard.move("c7","c5");
			testBoard.move("c5","c4");
			testBoard.move("d2","d4");
			testBoard.move("b2","b4");
			testBoard.move("c4","d3");
			assertNull(testBoard.getPiece("d4"));
			assertNull(testBoard.getPiece("c4"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}


}
