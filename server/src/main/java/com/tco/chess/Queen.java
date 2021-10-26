package com.tco.chess;

import java.util.ArrayList;

public class Queen extends ChessPiece{
	
	public Queen(ChessBoard board, Color color) {
		super(board, color);
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
		ArrayList<String> legalMoves = new ArrayList<>();

		try{
			//ChessBoard testBoard = ChessBoard();
			ChessPiece Bishop = new Bishop(board, this.color);
			Bishop.setPosition(getPosition());
			legalMoves = Bishop.legalMoves();

			ArrayList<String> getMoves = new ArrayList<>();
			ChessPiece Rook = new Rook(board, this.color);
			Rook.setPosition(getPosition());
			getMoves = Rook.legalMoves();
			
			for(String get:  getMoves){
				legalMoves.add(get);
			}
		} catch (IllegalPositionException e) {
			//Not a legal move
		}

		return legalMoves;
		
		
	}
}
