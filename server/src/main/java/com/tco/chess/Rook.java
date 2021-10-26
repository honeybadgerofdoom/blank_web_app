package com.tco.chess;

import java.util.ArrayList;

public class Rook extends ChessPiece {
	public Rook(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color.equals(Color.BLACK)) {
			return "\u265C"; //"\u265C" black rook
		}
		else {
			return "\u2656"; //"\u2656" white rook
		}
	}
	
	@Override
	public ArrayList<String> legalMoves(){
		ArrayList<String> legalMoves = new ArrayList<>();

		// NOTE The castling positions are ALREADY in the Rook's legalMoves array

		addMovesInDirection(legalMoves, 1, 0);
		addMovesInDirection(legalMoves, -1, 0);
		addMovesInDirection(legalMoves, 0, 1);
		addMovesInDirection(legalMoves, 0, -1);

		return legalMoves;
	}
}
