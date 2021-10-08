package com.tco.chess;

import java.util.ArrayList;

public class Queen extends ChessPiece{
	
	public Queen(ChessBoard board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if(this.color.equals(Color.BLACK)) {
			return "\u265B"; //"\u265B" black queen
		}
		else {
			return "\u2655"; // "\u2655" white queen
		}
	}
	
	@Override
	public ArrayList<String> legalMoves(){
		return new ArrayList<String>();
	}
}
