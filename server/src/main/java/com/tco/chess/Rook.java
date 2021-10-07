package a2;

import java.util.ArrayList;

public class Rook extends ChessPiece {
	public Rook(ChessBoard board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
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
		int col = this.column;
		int row = this.row;
		
		for(int i = col+1; i < 8; i++) {
			char colChar = (char) (i + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (row + '1');
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
		
		for(int i = row+1; i < 8; i++) {
			char colChar = (char) (col + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (i + '1');
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
		for(int i = col-1; i >= 0; i--) {
			char colChar = (char) (i + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (row + '1');
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
		for(int i = row-1; i >= 0; i--) {
			char colChar = (char) (col + 'a');
			String positionalStr = "";
			positionalStr += colChar;
			positionalStr += (char) (i + '1');
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
