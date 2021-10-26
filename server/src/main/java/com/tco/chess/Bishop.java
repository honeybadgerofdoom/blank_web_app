package com.tco.chess;

import java.util.ArrayList;

public class Bishop extends ChessPiece {
	
	public Bishop(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color.equals(Color.BLACK)) {
			return "\u265D"; //"\u265D" black bishop
		}
		else {
			return "\u2657"; // "\u2657" white bishop
		}
	}
	
	@Override
	public ArrayList<String> legalMoves(){
		ArrayList<String> legalMoves = new ArrayList<>();

		addMovesInDirection(legalMoves, 1, 1);
		addMovesInDirection(legalMoves, 1, -1);
		addMovesInDirection(legalMoves, -1, 1);
		addMovesInDirection(legalMoves, -1, -1);

		return legalMoves;
	}
}
