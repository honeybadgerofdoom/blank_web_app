package com.tco.chess;

import java.util.ArrayList;

public class King extends ChessPiece{
	public King(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color.equals(Color.BLACK)) {
			return "\u265A" ; //"\u265A" black king
		}
		else {
			return "\u2654"; // "\u2654" white king
		}
	}
	
	private boolean checkEmptySquares(int[] squaresToCheck, int row) {
		for (int square : squaresToCheck) {
			try {
				if (board.getPiece(rowColToPosition(row, square)) != null) return false;
			} catch (IllegalPositionException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	//The king can only move one square horizontally, vertically, or diagonally. Assume that it cannot castle3.
	@Override
	public ArrayList<String> legalMoves(){
		ArrayList<String> legalMoves = new ArrayList<>();
		String str = this.getPosition();

		// FIXME check for hasMoved, add moves accordingly
		int kingRow = color == Color.WHITE ? 0 : 7;
		int[] squaresForQueensideCastle = {1, 2, 3};
		int[] squaresForKingsideCastle = {5, 6};
		boolean kingRookHasntMoved = true;
		boolean queenRookHasntMoved = true;
		try {
			queenRookHasntMoved = board.getPiece(rowColToPosition(kingRow, 0)).hasMoved;
			kingRookHasntMoved = board.getPiece(rowColToPosition(kingRow, 7)).hasMoved;
		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
		boolean queensideSquaresEmpty = checkEmptySquares(squaresForQueensideCastle, kingRow);
		boolean kingsideSquaresEmpty = checkEmptySquares(squaresForKingsideCastle, kingRow);

		boolean queensideCastle = queenRookHasntMoved && queensideSquaresEmpty && !this.hasMoved;
		boolean kingsideCastle = kingRookHasntMoved && kingsideSquaresEmpty && !this.hasMoved;

		if (queensideCastle) {
			legalMoves.add(rowColToPosition(kingRow, 2));
		}

		if (kingsideCastle) {
			legalMoves.add(rowColToPosition(kingRow, 6));
		}

		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				
				StringBuilder potentialPos = new StringBuilder();
				
				if(((str.charAt(0) + i) >= 'a' && (str.charAt(0) + i) <= 'h' )) {
					if(((str.charAt(1) + j) >= '1' && (str.charAt(1) + j) <= '8' )) {
						potentialPos.append((char)(str.charAt(0) + i));
						potentialPos.append((char)(str.charAt(1) + j));
						
						try {
							if(! (board.getPiece(potentialPos.toString()) != null && board.getPiece(potentialPos.toString()).getColor().equals(this.getColor()))) {
								legalMoves.add(potentialPos.toString());
							}
						} catch (IllegalPositionException e) {
							e.printStackTrace();
						}
						
					}
				}
				
			}
		}
		
		return legalMoves;
	}
}
