package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;

class ChessBoardTest {
	private ChessBoard testBoard;
	
	@BeforeEach
	private void createTestBoard() {
		testBoard = new ChessBoard();
	}
	
	@Test
	void testBoardIsEmpty() {
		try {
			
			for(int i = 2; i < 6; i ++ ) {
				for(int j = 0; j < 8; j++) {
					String position = "";
					position += (char)(j + 'a');
					position += (char)(i + '1');
					
				
				assertNull(testBoard.getPiece(position));
					
					
				}
			}
			
			} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetPieceKing() {
		testBoard.initialize();
		try {
			assertTrue(testBoard.getPiece("e1") instanceof King);
			assertTrue(testBoard.getPiece("e1").getColor().equals(Color.WHITE));
			} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testInitialize() {
		testBoard.initialize();
		try {
			assertTrue(testBoard.getPiece("e1") instanceof King);
			assertTrue(testBoard.getPiece("e1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("e8") instanceof King);
			assertTrue(testBoard.getPiece("e8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("d1") instanceof Queen);
			assertTrue(testBoard.getPiece("d1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("d8") instanceof Queen);
			assertTrue(testBoard.getPiece("d8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("c1") instanceof Bishop);
			assertTrue(testBoard.getPiece("c1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("c8") instanceof Bishop);
			assertTrue(testBoard.getPiece("c8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("f1") instanceof Bishop);
			assertTrue(testBoard.getPiece("f1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("f8") instanceof Bishop);
			assertTrue(testBoard.getPiece("f8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("b1") instanceof Knight);
			assertTrue(testBoard.getPiece("b1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("b8") instanceof Knight);
			assertTrue(testBoard.getPiece("b8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("g1") instanceof Knight);
			assertTrue(testBoard.getPiece("g1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("g8") instanceof Knight);
			assertTrue(testBoard.getPiece("g8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("a1") instanceof Rook);
			assertTrue(testBoard.getPiece("a1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("a8") instanceof Rook);
			assertTrue(testBoard.getPiece("a8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("h1") instanceof Rook);
			assertTrue(testBoard.getPiece("h1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("h8") instanceof Rook);
			assertTrue(testBoard.getPiece("h8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("a2") instanceof Pawn);
			assertTrue(testBoard.getPiece("a2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("b2") instanceof Pawn);
			assertTrue(testBoard.getPiece("b2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("c2") instanceof Pawn);
			assertTrue(testBoard.getPiece("c2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("d2") instanceof Pawn);
			assertTrue(testBoard.getPiece("d2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("e2") instanceof Pawn);
			assertTrue(testBoard.getPiece("e2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("f2") instanceof Pawn);
			assertTrue(testBoard.getPiece("f2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("g2") instanceof Pawn);
			assertTrue(testBoard.getPiece("g2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("h2") instanceof Pawn);
			assertTrue(testBoard.getPiece("h2").getColor().equals(Color.WHITE));
			
			assertTrue(testBoard.getPiece("a7") instanceof Pawn);
			assertTrue(testBoard.getPiece("a7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("b7") instanceof Pawn);
			assertTrue(testBoard.getPiece("b7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("c7") instanceof Pawn);
			assertTrue(testBoard.getPiece("c7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("d7") instanceof Pawn);
			assertTrue(testBoard.getPiece("d7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("e7") instanceof Pawn);
			assertTrue(testBoard.getPiece("e7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("f7") instanceof Pawn);
			assertTrue(testBoard.getPiece("f7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("g7") instanceof Pawn);
			assertTrue(testBoard.getPiece("g7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("h7") instanceof Pawn);
			assertTrue(testBoard.getPiece("h7").getColor().equals(Color.BLACK));
			
			//Check if the rest of the board is null
			
			for(int i = 2; i < 6; i ++ ) {
				for(int j = 0; j < 8; j++) {
					String position = "";
					position += (char)(j + 'a');
					position += (char)(i + '1');
					
					try {
						assertNull(testBoard.getPiece(position));
					}catch(Exception e) {
						e.printStackTrace();
						fail();
					}
					
				}
			}

		
			} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testGetPieceInvalidInput() {
			
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			testBoard.getPiece("1e");
		});
		
	}
	void testGetPieceInvalidInput_0() {
		
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			testBoard.getPiece("");
		});
		
	}
	void testGetPieceInvalidInput_1() {
		
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			testBoard.getPiece("0000");
		});
		
	}
	
	void testGetPieceInvalidInput_3() {
		
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			testBoard.getPiece("jk");
		});
		
	}
	
	@Test
	void testGetPieceInvalidInput_2() {
			
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			testBoard.getPiece("a9");
		});
		
	}
	
	
	@Test
	void testPlacePiece() {
		testBoard.placePiece(new King(testBoard, Color.BLACK), "b2");
		try {
			assertTrue(testBoard.getPiece("b2") instanceof King);
			assertTrue(testBoard.getPiece("b2").getColor().equals(Color.BLACK));
			
			assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "b2"));
			assertTrue(testBoard.placePiece(new King(testBoard, Color.BLACK), "b3"));
			assertTrue(testBoard.placePiece(new King(testBoard, Color.BLACK), "a1"));
			assertTrue(testBoard.placePiece(new King(testBoard, Color.BLACK), "a8"));
			assertTrue(testBoard.placePiece(new King(testBoard, Color.BLACK), "h8"));
			assertTrue(testBoard.placePiece(new King(testBoard, Color.BLACK), "h1"));
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testPlacePieceInvalid() {
		
			assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "a9"));
			assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), ""));
			assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "99"));
			assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "00000"));
			assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "hj"));

	}
	
	@Test
	void testPlacePieceFriend() {
		testBoard.initialize();
		assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "b7"));
		assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), "a9"));
		assertFalse(testBoard.placePiece(new King(testBoard, Color.BLACK), ""));
		

	}
	
	
	@Test
	void testBoardIsNull() {
		
		try {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					
					char row = (char)(i + '1');
					char column = (char)(j + 'a');

					StringBuilder sb = new StringBuilder("");
					String str = sb.append(column).append(row).toString();
					
					if(testBoard.getPiece(str) != null) {
						fail();
					}
				}
			}
			
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	void testMove() {
//			
//		Assertions.assertThrows(IllegalMoveException.class, () ->{
//			testBoard.getPiece("a9");
//		});
//		
//	}
	
	

	@Test
	void testMove() {
		testBoard.placePiece(new King(testBoard, Color.BLACK), "e2");
		try {
			
			testBoard.move("e2", "e3");
			assertTrue(testBoard.getPiece("e3") instanceof King);
			assertTrue(testBoard.getPiece("e3").getColor().equals(Color.BLACK));
			assertNull(testBoard.getPiece("e2"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Other tests:::::::::: 
	
	@Test
	void initialQueenMove() {
		testBoard.initialize();
		try {
			testBoard.move("d2", "d3");
		} catch (IllegalMoveException e) {
			e.printStackTrace();
			fail();
		}
		assertThrows(IllegalMoveException.class, () -> {
			testBoard.move("d1", "d2");
		});
	}
	
	@Test
	void rowColCheck() {
		
		try {
			
			ChessPiece p = new Knight(testBoard, Color.BLACK);
			ChessPiece king = new King(testBoard, Color.BLACK);
			p.setPosition("b6");
			assertEquals(p.row, 5);
			assertEquals(p.column, 1);
			testBoard.placePiece(king, "h1");
			assertEquals(king.row, 0);
			assertEquals(king.column, 7);
			
			
		} catch (IllegalPositionException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void kingCapture() {
		testBoard.initialize();
		try {
			testBoard.move("e2", "e3");
			testBoard.move("e1", "e2");
			testBoard.move("e2", "d3");
			testBoard.move("d3", "d4");
			testBoard.move("d4", "d5");
			testBoard.move("d5", "e6");
			testBoard.move("e6", "e5");
			testBoard.move("e5", "f5");
			testBoard.move("f5", "f6");
			testBoard.move("f6", "g7");
			testBoard.move("g7", "g6");
			testBoard.move("g6", "f7");
			
			assertTrue(testBoard.getPiece("f7") instanceof King);
			assertTrue(testBoard.getPiece("f7").getColor().equals(Color.WHITE));
			assertNull(testBoard.getPiece("g6"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	void testMoveIllegal() {
		testBoard.initialize();
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("a2", "78");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testMoveIllegalNoPiece() {
		
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("a2", "a3");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testMoveNoArg() {
		
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("", "a3");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	@Test
	void testMoveInvalidArg() {
		
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("asd", "a3");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testMoveInvalidArg_1() {
		testBoard.initialize();
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("asd", "a3");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testMoveInvalid_2Arg() {
		testBoard.initialize();
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("a2", "sdf");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	@Test
	void testMoveNoArgs() {
		
		try {
	
			Assertions.assertThrows(IllegalMoveException.class, () ->{
				testBoard.move("a2", "");
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void movePieceBoardStaySameOtherwise() {
		testBoard.initialize();
		
		try {
	
			testBoard.move("a2", "a4");
			assertTrue(testBoard.getPiece("e1") instanceof King);
			assertTrue(testBoard.getPiece("e1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("e8") instanceof King);
			assertTrue(testBoard.getPiece("e8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("d1") instanceof Queen);
			assertTrue(testBoard.getPiece("d1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("d8") instanceof Queen);
			assertTrue(testBoard.getPiece("d8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("c1") instanceof Bishop);
			assertTrue(testBoard.getPiece("c1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("c8") instanceof Bishop);
			assertTrue(testBoard.getPiece("c8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("f1") instanceof Bishop);
			assertTrue(testBoard.getPiece("f1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("f8") instanceof Bishop);
			assertTrue(testBoard.getPiece("f8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("b1") instanceof Knight);
			assertTrue(testBoard.getPiece("b1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("b8") instanceof Knight);
			assertTrue(testBoard.getPiece("b8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("g1") instanceof Knight);
			assertTrue(testBoard.getPiece("g1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("g8") instanceof Knight);
			assertTrue(testBoard.getPiece("g8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("a1") instanceof Rook);
			assertTrue(testBoard.getPiece("a1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("a8") instanceof Rook);
			assertTrue(testBoard.getPiece("a8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("h1") instanceof Rook);
			assertTrue(testBoard.getPiece("h1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("h8") instanceof Rook);
			assertTrue(testBoard.getPiece("h8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("a4") instanceof Pawn);
			assertTrue(testBoard.getPiece("a4").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("b2") instanceof Pawn);
			assertTrue(testBoard.getPiece("b2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("c2") instanceof Pawn);
			assertTrue(testBoard.getPiece("c2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("d2") instanceof Pawn);
			assertTrue(testBoard.getPiece("d2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("e2") instanceof Pawn);
			assertTrue(testBoard.getPiece("e2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("f2") instanceof Pawn);
			assertTrue(testBoard.getPiece("f2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("g2") instanceof Pawn);
			assertTrue(testBoard.getPiece("g2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("h2") instanceof Pawn);
			assertTrue(testBoard.getPiece("h2").getColor().equals(Color.WHITE));
			
			assertTrue(testBoard.getPiece("a7") instanceof Pawn);
			assertTrue(testBoard.getPiece("a7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("b7") instanceof Pawn);
			assertTrue(testBoard.getPiece("b7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("c7") instanceof Pawn);
			assertTrue(testBoard.getPiece("c7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("d7") instanceof Pawn);
			assertTrue(testBoard.getPiece("d7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("e7") instanceof Pawn);
			assertTrue(testBoard.getPiece("e7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("f7") instanceof Pawn);
			assertTrue(testBoard.getPiece("f7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("g7") instanceof Pawn);
			assertTrue(testBoard.getPiece("g7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("h7") instanceof Pawn);
			assertTrue(testBoard.getPiece("h7").getColor().equals(Color.BLACK));
				
			//Check if the rest of the board is null
			
			for(int i = 2; i < 6; i ++ ) {
				for(int j = 0; j < 8; j++) {
					String position = "";
					position += (char)(j + 'a');
					position += (char)(i + '1');
					
						if(!position.equals("a4")) {
							assertNull(testBoard.getPiece(position));
						}
					
					
				}
			}
			assertNull(testBoard.getPiece("a2"));

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void movePieceBoardStaySameOtherwisePP() {
		testBoard.initialize();
		
		try {
			testBoard.placePiece(new King(testBoard, Color.BLACK), "e5");
			
			assertTrue(testBoard.getPiece("e1") instanceof King);
			assertTrue(testBoard.getPiece("e1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("e8") instanceof King);
			assertTrue(testBoard.getPiece("e8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("d1") instanceof Queen);
			assertTrue(testBoard.getPiece("d1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("d8") instanceof Queen);
			assertTrue(testBoard.getPiece("d8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("c1") instanceof Bishop);
			assertTrue(testBoard.getPiece("c1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("c8") instanceof Bishop);
			assertTrue(testBoard.getPiece("c8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("f1") instanceof Bishop);
			assertTrue(testBoard.getPiece("f1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("f8") instanceof Bishop);
			assertTrue(testBoard.getPiece("f8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("b1") instanceof Knight);
			assertTrue(testBoard.getPiece("b1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("b8") instanceof Knight);
			assertTrue(testBoard.getPiece("b8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("g1") instanceof Knight);
			assertTrue(testBoard.getPiece("g1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("g8") instanceof Knight);
			assertTrue(testBoard.getPiece("g8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("a1") instanceof Rook);
			assertTrue(testBoard.getPiece("a1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("a8") instanceof Rook);
			assertTrue(testBoard.getPiece("a8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("h1") instanceof Rook);
			assertTrue(testBoard.getPiece("h1").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("h8") instanceof Rook);
			assertTrue(testBoard.getPiece("h8").getColor().equals(Color.BLACK));
			
			assertTrue(testBoard.getPiece("a2") instanceof Pawn);
			assertTrue(testBoard.getPiece("a2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("b2") instanceof Pawn);
			assertTrue(testBoard.getPiece("b2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("c2") instanceof Pawn);
			assertTrue(testBoard.getPiece("c2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("d2") instanceof Pawn);
			assertTrue(testBoard.getPiece("d2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("e2") instanceof Pawn);
			assertTrue(testBoard.getPiece("e2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("f2") instanceof Pawn);
			assertTrue(testBoard.getPiece("f2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("g2") instanceof Pawn);
			assertTrue(testBoard.getPiece("g2").getColor().equals(Color.WHITE));
			assertTrue(testBoard.getPiece("h2") instanceof Pawn);
			assertTrue(testBoard.getPiece("h2").getColor().equals(Color.WHITE));
			
			assertTrue(testBoard.getPiece("a7") instanceof Pawn);
			assertTrue(testBoard.getPiece("a7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("b7") instanceof Pawn);
			assertTrue(testBoard.getPiece("b7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("c7") instanceof Pawn);
			assertTrue(testBoard.getPiece("c7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("d7") instanceof Pawn);
			assertTrue(testBoard.getPiece("d7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("e7") instanceof Pawn);
			assertTrue(testBoard.getPiece("e7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("f7") instanceof Pawn);
			assertTrue(testBoard.getPiece("f7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("g7") instanceof Pawn);
			assertTrue(testBoard.getPiece("g7").getColor().equals(Color.BLACK));
			assertTrue(testBoard.getPiece("h7") instanceof Pawn);
			assertTrue(testBoard.getPiece("h7").getColor().equals(Color.BLACK));
				
			//Check if the rest of the board is null
			
			for(int i = 2; i < 6; i ++ ) {
				for(int j = 0; j < 8; j++) {
					String position = "";
					position += (char)(j + 'a');
					position += (char)(i + '1');
					
					if(!position.equals("e5")) {
						assertNull(testBoard.getPiece(position));
					}
				}
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void queenCantMove() {
		testBoard.initialize();
		try {
			
			Assertions.assertThrows(IllegalMoveException.class, () -> {
			testBoard.move("e2", "e4");
			testBoard.move("d1", "h5");
			testBoard.move("h5", "f7");
		
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void knightCantMove() {
		testBoard.initialize();
		try {
			
			Assertions.assertThrows(IllegalMoveException.class, () -> {
			testBoard.move("b8", "e4");
		
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	

	@Test
	void rookCapture() {
		testBoard.initialize();
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
			testBoard.move("e5", "f5");
			
			assertTrue(testBoard.getPiece("f5") instanceof Rook);
			assertTrue(testBoard.getPiece("f5").getColor().equals(Color.WHITE));
			assertNull(testBoard.getPiece("e5"));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void bishopCapture() {
		testBoard.initialize();
		try {
			
			ChessPiece bishop = new Bishop(testBoard, Color.WHITE);
			testBoard.placePiece(bishop, "f5");
			testBoard.move("f5", "d7");
			
			assertTrue(testBoard.getPiece("d7") instanceof Bishop);
			assertTrue(testBoard.getPiece("d7").getColor().equals(Color.WHITE));
			assertNull(testBoard.getPiece("f5"));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void pawnCapture() {
		testBoard.initialize();
		try {
			
			ChessPiece pawn = new Pawn(testBoard, Color.WHITE);
			testBoard.placePiece(pawn, "b6");
			testBoard.move("b6", "c7");
			
			assertTrue(testBoard.getPiece("c7") instanceof Pawn);
			assertTrue(testBoard.getPiece("c7").getColor().equals(Color.WHITE));
			assertNull(testBoard.getPiece("b6"));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void pawnCaptureBlack() {
		testBoard.initialize();
		try {
			
			ChessPiece pawn = new Pawn(testBoard, Color.BLACK);
			testBoard.placePiece(pawn, "d3");
			testBoard.move("d3", "c2");
			
			assertTrue(testBoard.getPiece("c2") instanceof Pawn);
			assertTrue(testBoard.getPiece("c2").getColor().equals(Color.BLACK));
			assertNull(testBoard.getPiece("d3"));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	// ChessPiece tests
	
	@Test
	void testGetPosition() {
		testBoard.initialize();

		try {
			ChessPiece piece = testBoard.getPiece("e1");
			String testStr = piece.getPosition();
			
			assertEquals("e1", testStr);
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	void testSetPosition() {
		testBoard.initialize();
		
		try {
			ChessPiece piece = testBoard.getPiece("a2");
			piece.setPosition("a3");
			
			assertEquals("a3", piece.getPosition());
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testSetPositionInvalid() {
		testBoard.initialize();
		
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			ChessPiece piece = testBoard.getPiece("a2");
			piece.setPosition("a10");
		});
		
	}
	
	@Test
	void testGetPositionInvalid() {
		testBoard.initialize();
		
		Assertions.assertThrows(IllegalPositionException.class, () ->{
			ChessPiece piece = testBoard.getPiece("a-10");
			piece.setPosition("a10");
		});
		
	}
	
	
	@Test
	void testGetColor() {
		testBoard.initialize();
		
		try {
			ChessPiece piece = testBoard.getPiece("a2");
			
			assertTrue(piece.getColor().equals(Color.WHITE));
			
			
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

