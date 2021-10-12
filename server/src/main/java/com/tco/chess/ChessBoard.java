package com.tco.chess;

import java.util.ArrayList;

import com.tco.chess.ChessPiece.Color;

public class ChessBoard {
	private ChessPiece[][] board;
	private Color winner;
	private int[] piecesCaptured;
	/*
	INDICES
	0: white pawns
	1: white rooks
	2: white knights
	3: white bishops
	4: white queens
	5: white kings
	6: black pawns
	7: black rooks
	8: black knights
	9: black bishops
	10: black queens
	11: black kings

	After each move is made, check if a pieces was captured. If so, increment that index in the capturedPieces array.
	If promotion occurred, decrement that location in the array.
	Then, check if index 0 or 6 == 6, indices 1-3, 7-9 == 2, or 4-5, 10-11 == 1. If true, declare winner.
	 */
	
	public ChessBoard() {
		board = new ChessPiece[8][8];// Each place needs to be null 		
	}


	public void  initialize() {
		winner = null;
		piecesCaptured = new int[12];
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
	
			return true; 
		
		}catch(Exception e) {
			System.out.println("There was an exception in placePiece");
			e.printStackTrace();
		}
		return false;
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
				if(getPiece(toPosition) != null && getPiece(toPosition).getColor() != piece.getColor()) {
					handleCapture(getPiece(toPosition));
				}
				placePiece(piece, toPosition);
			}
			else {
				throw new IllegalMoveException("Illegal Move attempted");
			}
			
		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
	}

	private handleCapture(ChessPiece captured) {
		Color color = captured.getColor();
		int incrementBasedOnColor = color == Color.WHITE ? 0 : 6;
		int capturedIndex = -1; //FIXME can this be uninitialized?
		if(captured instanceof Pawn) {
			capturedIndex = 0 + incrementBasedOnColor;
		}
		else if(captured instanceof Rook) {
			capturedIndex = 1 + incrementBasedOnColor;
		}
		else if(captured instanceof Knight) {
			capturedIndex = 2 + incrementBasedOnColor;
		}
		else if(captured instanceof Bishop) {
			capturedIndex = 3 + incrementBasedOnColor;
		}
		else if(captured instanceof Queen) {
			capturedIndex = 4 + incrementBasedOnColor;
		}
		else if(captured instanceof King) {
			capturedIndex = 5 + incrementBasedOnColor;
		}
		else {
			System.out.println("ERROR in hanldeCapture()");
		}
		piecesCaptured[capturedIndex]++;
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
