package com.tco.chess;

import java.util.ArrayList;

public class Knight extends ChessPiece{

	public Knight(ChessBoard board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if(this.color.equals(Color.BLACK)) {
			return "\u265E"; //"\u265E" black knight
		}
		else {
			return "\u2658"; //"\u2658" white knight
		}
	}
	
	@Override
	public ArrayList<String> legalMoves(){
		return new ArrayList<String>();
	}
}
