package com.tco.chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece{
	int numberOfMoves;

	public Pawn(ChessBoard board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if(this.color.equals(Color.BLACK)) {
			return "\u265F"; //"\u265F" black pawn
		}
		else {
			return "\u2659"; // "\u2659" white pawn
		}
	}
		
	@Override
	public ArrayList<String> legalMoves(){
		ArrayList<String> legalMoves = new ArrayList<>();
		int col = this.column;
		int row = this.row;
		
		int moveDistance = (this.getColor() == Color.WHITE && row == 1) || (this.getColor() == Color.BLACK && row == 6) ? 2 : 1;

		try {
			if (row <= 7 && row >= 0){
				int enemyRow = (this.getColor() == Color.WHITE) ? row + 1 : row - 1;

				ChessPiece enemyPieceR = null;
				String enemyStrR = "";
			
			if(col > 0 && enemyRow <= 8 && enemyRow >= 0) {
				enemyStrR += (char)((col + 'a') - 1);
				enemyStrR += (char)(enemyRow + '1');
				
				if(enemyStrR.length() == 2 && ChessBoard.validatePosition(enemyStrR)) {
					enemyPieceR = board.getPiece(enemyStrR);
				}
			}

			ChessPiece enemyPieceL = null;
			String enemyStrL = "";
			
			if(col < 7 && enemyRow <= 8 && enemyRow >= 0) {
				enemyStrL += (char)((col + 'a') + 1);
				enemyStrL += (char)(enemyRow + '1');
				
				if(enemyStrL.length() == 2 && ChessBoard.validatePosition(enemyStrL)) {
					enemyPieceL = board.getPiece(enemyStrL);
				}
			}

			if(enemyPieceL != null && this.getColor() != enemyPieceL.getColor()) {
				legalMoves.add(enemyStrL);
			}
			if(enemyPieceR != null && this.getColor() != enemyPieceR.getColor()) {
				legalMoves.add(enemyStrR);
			}
		}

		for(int i = 0; i < moveDistance; i++) {
			char colChar = (char) (col + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (this.getColor() == Color.WHITE ? row + i + 1 + '1' : row - i - 1 + '1');

			if (ChessBoard.validatePosition(positionalStr) && board.getPiece(positionalStr) == null) {
				if (i == 1 && legalMoves.isEmpty()) {
					break;
				} else {
					legalMoves.add(positionalStr);
				}
			}
		}

	}catch(Exception e) {
		e.printStackTrace();
	}
		return legalMoves;
	}

	public ArrayList<String> enPassant(String position){
		ArrayList<String> legalMoves = new ArrayList<>();
		int col = this.column;
		int row = this.row;







	}

}


