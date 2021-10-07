package a2;

import java.util.ArrayList;

public class Pawn extends ChessPiece{
	
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
		
	@Override
	public ArrayList<String> legalMoves(){
		ArrayList<String> legalMoves = new ArrayList<>();
		int col = this.column;
		int row = this.row;
		
		int moveDistance = (this.getColor() == Color.WHITE && row == 1) || (this.getColor() == Color.BLACK && row == 6) ? 2 : 1;
		
		int enemyRow;
		
		try {
		if (row <= 7 && row >= 0){
			if(this.getColor() == Color.WHITE) {
				enemyRow = row + 1;
			}
			else {
			    enemyRow = row - 1;
			}
			
			ChessPiece enemyPieceR = null;
			String enemyStrR = "";
			
			if(col > 0 && enemyRow <= 8 && enemyRow >= 0) {
				enemyStrR = "";
				enemyStrR += (char)((col + 'a') - 1);
				enemyStrR += (char)(enemyRow + '1');
				
				
				if(enemyStrR.length() > 0 && validatePosition(enemyStrR)) {
					enemyPieceR = board.getPiece(enemyStrR);
				}
			}
			ChessPiece enemyPieceL = null;
			String enemyStrL = "";
			
			if(col < 7 && enemyRow <= 8 && enemyRow >= 0) {
				enemyStrL += (char)((col + 'a') + 1);
				enemyStrL += (char)(enemyRow + '1');
				
				if(enemyStrL.length() > 0 && validatePosition(enemyStrL)) {
					enemyPieceL = board.getPiece(enemyStrL);
				}
			}
			
			if(enemyPieceL != null && this.getColor() != enemyPieceL.getColor() && enemyStrL.length() != 0) {
				legalMoves.add(enemyStrL);
			}
			
			if(enemyPieceR != null && this.getColor() != enemyPieceR.getColor() && enemyStrR.length() != 0) {
				legalMoves.add(enemyStrR);
			}
			
		}
		

		for(int i = 0; i < moveDistance; i++) {
			char colChar = (char) (col + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (this.getColor() == Color.WHITE ? row + i + 1 + '1' : row - i - 1 + '1');
			
			if(validatePosition(positionalStr) && board.getPiece(positionalStr) == null) {
				legalMoves.add(positionalStr);
			}
			
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
		
		return legalMoves;
	}
	

}


