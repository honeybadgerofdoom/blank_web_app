package a2;

import java.util.ArrayList;

public class Bishop extends ChessPiece {
	
	public Bishop(ChessBoard board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
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
		int col = this.column;
		int row = this.row;
		
		for(int i = col+1, j = row+1; i < 8 && j < 8; i++, j++ ) {
			char colChar = (char) (i + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (j + '1');
			try {
				if (board.getPiece(positionalStr) != null) {
					if (!board.getPiece(positionalStr).getColor().equals(this.getColor())) {
						legalMoves.add(positionalStr);
					}
					break;
				}
				else {
					legalMoves.add(positionalStr);
				}
			} catch (IllegalPositionException e) {
				//Not a legal move
				break;
			}
		}
		for(int i = col-1, j = row-1; i >= 0 && j >= 0; i--, j-- ) {
			char colChar = (char) (i + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (j + '1');
			try {
				if (board.getPiece(positionalStr) != null) {
					if (!board.getPiece(positionalStr).getColor().equals(this.getColor())) {
						legalMoves.add(positionalStr);
					}
					break;
				}
				else {
					legalMoves.add(positionalStr);
				}
			} catch (IllegalPositionException e) {
				//Not a legal move
				break;
			}
		}
		for(int i = col+1, j = row-1; i < 8 && j >= 0; i++, j-- ) {
			char colChar = (char) (i + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (j + '1');
			try {
				if (board.getPiece(positionalStr) != null) {
					if (!board.getPiece(positionalStr).getColor().equals(this.getColor())) {
						legalMoves.add(positionalStr);
					}
					break;
				}
				else {
					legalMoves.add(positionalStr);
				}
			} catch (IllegalPositionException e) {
				//Not a legal move
				break;
			}
		}
		for(int i = col-1, j = row+1; i >= 0 && j < 8; i--, j++ ) {
			char colChar = (char) (i + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (j + '1');
			try {
				if (board.getPiece(positionalStr) != null) {
					if (!board.getPiece(positionalStr).getColor().equals(this.getColor())) {
						legalMoves.add(positionalStr);
					}
					break;
				}
				else {
					legalMoves.add(positionalStr);
				}
			} catch (IllegalPositionException e) {
				//Not a legal move
				break;
			}
		}
		
		return legalMoves;
	}
}
