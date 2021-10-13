package com.tco.chess;

import java.util.ArrayList;

import com.tco.chess.ChessPiece.Color;

public class ChessBoard {
	private ChessPiece[][] board;
	
	
	public ChessBoard() {
		board = new ChessPiece[8][8];// Each place needs to be null 		
	}
	
	
	public void  initialize() {

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 2; j++) {
				placePiece(new Rook(this, j == 0 ? Color.WHITE : Color.BLACK), "a"+ (j*7+1));
				placePiece(new Knight(this, j == 0 ? Color.WHITE : Color.BLACK), "b"+ (j*7+1));
				placePiece(new Bishop(this, j == 0 ? Color.WHITE : Color.BLACK), "c"+ (j*7+1));
				placePiece(new Queen(this, j == 0 ? Color.WHITE : Color.BLACK), "d"+ (j*7+1));
				placePiece(new King(this, j == 0 ? Color.WHITE : Color.BLACK), "e"+ (j*7+1));
				placePiece(new Bishop(this, j == 0 ? Color.WHITE : Color.BLACK), "f"+ (j*7+1));
				placePiece(new Knight(this, j == 0 ? Color.WHITE : Color.BLACK), "g"+ (j*7+1));
				placePiece(new Rook(this, j == 0 ? Color.WHITE : Color.BLACK), "h"+ (j*7+1));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "a"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "b"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "c"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "d"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "e"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "f"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "g"+ (j*5+2));
				placePiece(new Pawn(this, j == 0 ? Color.WHITE : Color.BLACK), "h"+ (j*5+2));
			}
		}
		
		for(int i = 2; i < 6; i ++ ) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
		
	}
	
	
	private boolean validatePosition(String position) {
		
		if(position.length() != 2) {
			return false;
		}
		if(!(position.charAt(0) >= 'a' && position.charAt(0) <= 'h' )) {
			return false;
		}
		if(!(position.charAt(1) >= '1' && position.charAt(1) <= '8')) {
			return false;
		}
		return true;
	}
	
	
	public ChessPiece getPiece(String position) throws IllegalPositionException {
		
		if(!validatePosition(position)) {
			throw new IllegalPositionException("Invalid Board Position");
		}
		
		int[] arr = boardRowCol(position);
		
		return board[arr[0]][arr[1]]; //0 = row, 1 = col
			
	}
	
	
	private int[] boardRowCol(String position) throws IllegalPositionException {
		
		if(validatePosition(position)) {
			int[] arr = new int[2];
			int column = position.charAt(0) % 'a';
			int row = position.charAt(1) % '1'; 
			
			arr[0] = row;
			arr[1] = column;
			return arr;
			
		}
		throw new IllegalPositionException("Error in boardRowCol");
	}
	
	private boolean checkPieceOnBoard(ChessPiece piece) {
		boolean onBoard = false;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j]!= null && board[i][j].equals(piece)) {
					onBoard = true;
				}
			}
		}
		return onBoard;
	}
	
	
	public boolean placePiece(ChessPiece piece, String position) {
		try {
		
			if(!validatePosition(position)) {
				return false;
			}
	
			
			ChessPiece pieceAtPosition = getPiece(position);
			int[] rowCol = boardRowCol(position);
			
			if(pieceAtPosition != null && pieceAtPosition.getColor().equals(piece.getColor())) {
				return false;
			}
			
			boolean onBoard = checkPieceOnBoard(piece);
			
			if(onBoard) {
				this.board[piece.row][piece.column] = null; 
			}
		
		//	this.board[piece.row][piece.column] = null; 
			this.board[rowCol[0]][rowCol[1]] = piece;

			piece.setPosition(position);

			// NOTE: At this point, check if the piece is a pawn, and if it is in a position to be promoted. If so,
			// call promotePawn(). Will implement this later as we really need client/API connection to implement
			// this in a meaningful way. Thus, this does not *enforce* pawn promotion, it merely makes it possible.

			// NOTE: The response for a /move request will include a boolean saying whether or not a pawn must
			// 	     be promoted. At which point a /promote request will be made, which will call promotePawn()
	
			return true; 
		
		}catch(Exception e) {
			System.out.println("There was an exception in placePiece");
			e.printStackTrace();
		}
		return false;
	}

	public void promotePawn(ChessPiece pawn, String promotion) throws IllegalPositionException, IllegalPromotionException {
		Color color = pawn.getColor();
		String position = pawn.getPosition();
		int[] rowCol = boardRowCol(position);
		int incrementForColor = color == Color.WHITE ? 7 : 0;
		if(!(pawn instanceof Pawn)) {
			throw new IllegalPromotionException("You can only promote pawns.");
		}
		else if(rowCol[0] != incrementForColor) {
			throw new IllegalPromotionException("You can only promote a pawn if it is on its enemy's first row");
		}
		else {
			//handleCapture(pawn); DO THIS once merged with win_condition logic
			board[rowCol[0]][rowCol[1]] = null;
			ChessPiece newPiece;
			switch (promotion) {
				case "Queen":
					newPiece = new Queen(this, color);
					placePiece(queen, position);
				case "King":
					newPiece = new King(this, color);
					placePiece(king, position);
				case "Rook":
					newPiece = new Rook(this, color);
					placePiece(rook, position);
				case "Knight":
					newPiece = new Knight(this, color);
					placePiece(knight, position);
				case "Bishop":
					newPiece = new Bishop(this, color);
					placePiece(bishop, position);
				default:
					System.out.println("You must enter either Queen, King, Rook, Bishop, or Knight");
			}
			//handlePromotion(newPiece); DO THIS once merged with win_condition logic
		}
	}

	public void move(String fromPosition, String toPosition) throws IllegalMoveException {
		
		 try {
			 
			if(!validatePosition(fromPosition) || !validatePosition(toPosition)) {
				throw new IllegalMoveException("Invalid Input in move");
			}
			
			ChessPiece piece = getPiece(fromPosition);
			
			if(piece == null) {
				throw new IllegalMoveException("No piece selected");
			}
			
			ArrayList<String> legalMoves = piece.legalMoves();
			
			if(legalMoves.contains(toPosition)) {
				placePiece(piece, toPosition);
			}
			else {
				throw new IllegalMoveException("Illegal Move attempted");
			}
			
		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
	}

	
	public String toString(){
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i<7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + " \u3000 ";
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}

}
