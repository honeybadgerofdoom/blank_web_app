package com.tco.chess;

import java.util.ArrayList;

public class Knight extends ChessPiece{

	public Knight(ChessBoard board, Color color) {
		super(board, color);
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
		ArrayList<String> legalMoves = new ArrayList<String>();
		int row = this.row;
		int col = this.column;

		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				if(((Math.abs(i) != Math.abs(j)) && (i != 0 && j != 0))) {
					if(((row + i + 1) <= 8 && (row + i + 1) > 0) && (col + j <= 8 && col + j >= 0)) {
						try {
							String positionString = "" + (Character.toString(((col + j)) + 'a')) + (i + row + 1);
							ChessPiece positionPiece = board.getPiece(positionString);
							if (board.getPiece(positionString) == null || positionPiece.getColor() != this.color) {
								legalMoves.add(positionString);
							}
						} catch (IllegalPositionException e) {
							//Not a legal move
							break;
						}
					}
				}
			}
		}
		return legalMoves;
	}
}
