package com.tco.chess;

import java.util.ArrayList;
import com.tco.chess.ChessPiece.Color;



public class ChessBoard {
	private ChessPiece[][] board;
	private Color turn;
	private Color winner = null;
	private int[] piecesRemaining = {8, 2, 2, 2, 1, 1, 8, 2, 2, 2, 1, 1};
	/*
	INDICES
	0: white pawns
	1: white rooks
	2: white knights
	3: white bishops
	4: white queens
	5: white kings
	6: black pawns
	7: black rooks
	8: black knights
	9: black bishops
	10: black queens
	11: black kings
	 */

	public ChessBoard() {
		board = new ChessPiece[8][8];
		turn = Color.WHITE;
	}

	public void initialize() {
		addNonPawns(8, Color.BLACK);
		addPawns(7, Color.BLACK);
		addPawns(2, Color.WHITE);
		addNonPawns(1, Color.WHITE);
	}

	private void addPawns(int row, Color color) {
		for (char colSymbol : "abcdefgh".toCharArray()) {
			placePiece(new Pawn(this, color), String.valueOf(colSymbol) + row);
		}
	}

	private void addNonPawns(int row, Color color) {
        ChessPiece[] pieces = {
                new Rook(this, color),
                new Knight(this, color),
                new Bishop(this, color),
                new Queen(this, color),
                new King(this, color),
                new Bishop(this, color),
                new Knight(this, color),
                new Rook(this, color)
        };

        String colSymbols = "abcdefgh";
        for (int i = 0; i < pieces.length; i++) {
            placePiece(pieces[i], String.valueOf(colSymbols.charAt(i)) + row);
        }
	}

	protected static boolean validatePosition(String position) {

		if(position.length() != 2) {
			return false;
		}
		if(!(position.charAt(0) >= 'a' && position.charAt(0) <= 'h' )) {
			return false;
		}
		return position.charAt(1) >= '1' && position.charAt(1) <= '8';
	}

	public ChessPiece getPiece(String position) throws IllegalPositionException {

		if(!validatePosition(position)) {
			throw new IllegalPositionException("Invalid Board Position");
		}

		int[] arr = boardRowCol(position);

		return board[arr[0]][arr[1]]; //0 = row, 1 = col

	}

	protected static int[] boardRowCol(String position) throws IllegalPositionException {

		if(validatePosition(position)) {
			int[] arr = new int[2];
			int column = position.charAt(0) % 'a';
			int row = position.charAt(1) % '1';

			arr[0] = row;
			arr[1] = column;
			return arr;

		}
		throw new IllegalPositionException("Error in boardRowCol");
	}

	private boolean checkPieceOnBoard(ChessPiece piece) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (board[i][j]!= null && board[i][j].equals(piece)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean placePiece(ChessPiece piece, String position) {
		try {

			if(!validatePosition(position)) {
				return false;
			}


			ChessPiece pieceAtPosition = getPiece(position);
			int[] rowCol = boardRowCol(position);

			if(pieceAtPosition != null && pieceAtPosition.getColor().equals(piece.getColor())) {
				return false;
			}

			boolean onBoard = checkPieceOnBoard(piece);

			if(onBoard) {
				this.board[piece.row][piece.column] = null;
			}

		//	this.board[piece.row][piece.column] = null;
			this.board[rowCol[0]][rowCol[1]] = piece;

			piece.setPosition(position);

			// NOTE: At this point, check if the piece is a pawn, and if it is in a position to be promoted. If so,
			// call promotePawn(). Will implement this later as we really need client/API connection to implement
			// this in a meaningful way. Thus, this does not *enforce* pawn promotion, it merely makes it possible.

			// NOTE: The response for a /move request will include a boolean saying whether or not a pawn must
			// 	     be promoted. At which point a /promote request will be made, which will call promotePawn()

			return true;

		}catch(Exception e) {
			System.out.println("There was an exception in placePiece");
			e.printStackTrace();
		}
		return false;
	}

	public void promotePawn(ChessPiece pawn, String promotion) throws IllegalPositionException, IllegalPromotionException {
		validatePromotion(pawn, promotion);

		Color color = pawn.getColor();
		String position = pawn.getPosition();
		int[] rowCol = boardRowCol(position);

		handleCapture(pawn);
		board[rowCol[0]][rowCol[1]] = null;
		ChessPiece promotedPiece = createPromotedPiece(promotion, color);
		placePiece(promotedPiece, position);
		handlePromotion(promotedPiece);
		checkIfTheGameIsOver();
	}

	private void validatePromotion(ChessPiece pawn, String promotion) throws IllegalPositionException, IllegalPromotionException {
		if(!validatePromotionString(promotion)) {
			throw new IllegalPromotionException("You can't promote to a " + promotion);
		}
		else if(!(pawn instanceof Pawn)) {
			throw new IllegalPromotionException("You can only promote pawns.");
		}
		Color color = pawn.getColor();
		String position = pawn.getPosition();
		int[] rowCol = boardRowCol(position);
		int promotionRow = color == Color.WHITE ? 7 : 0;
		if(rowCol[0] != promotionRow) {
			throw new IllegalPromotionException("You can only promote a pawn if it is on its enemy's first row");
		}
	}

	private ChessPiece createPromotedPiece(String promotion, Color color) {
		switch (promotion) {
			case "Queen":
				return new Queen(this, color);
			case "King":
				return new King(this, color);
			case "Rook":
				return new Rook(this, color);
			case "Knight":
				return new Knight(this, color);
			default:
				return new Bishop(this, color);
		}
	}

	private boolean validatePromotionString(String promotion) {
		String[] validPromotions = {"Rook", "Knight", "Bishop", "Queen", "King"};
		for(String piece : validPromotions) {
			if(piece.equals(promotion)) return true;
		}
		return false;
	}

	public void move(String fromPosition, String toPosition) throws IllegalMoveException {

		if(winner != null) {
			throw new IllegalMoveException("The game is already over.");
		}

		 try {

			if(!validatePosition(fromPosition) || !validatePosition(toPosition)) {
				throw new IllegalMoveException("Invalid Input in move");
			}

			ChessPiece piece = getPiece(fromPosition);

			if(piece == null) {
				throw new IllegalMoveException("No piece selected");
			}

			ArrayList<String> legalMoves = piece.legalMoves();

			if(legalMoves.contains(toPosition)) {
				if(piece instanceof Pawn && getPiece(toPosition) == null && moveIsDiagonal(fromPosition, toPosition)){

				}

				if(getPiece(toPosition) != null && getPiece(toPosition).getColor() != piece.getColor()) {
					handleCapture(getPiece(toPosition));
				}
				placePiece(piece, toPosition);
				if ((piece instanceof Rook || piece instanceof King) && !piece.hasMoved) piece.hasMoved = true;
				if (piece instanceof Pawn) piece.numberOfMoves++;
				switchTurn();
				checkIfTheGameIsOver();
			}
			else {
				throw new IllegalMoveException("Illegal Move attempted");
			}

		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
	}

	private void checkIfTheGameIsOver() {
		for(int i = 0; i < piecesRemaining.length; i++) {
			if(piecesRemaining[i] == 0) {
				winner = i < 6 ? Color.BLACK : Color.WHITE;
			}
		}
	}

	private void handleCapture(ChessPiece captured) {
		int capturedIndex = getIndexInPiecesRemaining(captured);
		piecesRemaining[capturedIndex]--;
	}

	private void handlePromotion(ChessPiece promoted) {
		int promotedIndex = getIndexInPiecesRemaining(promoted);
		piecesRemaining[promotedIndex]++;
	}

	private int getIndexInPiecesRemaining(ChessPiece piece) {
		int incrementBasedOnColor = piece.getColor() == Color.WHITE ? 0 : 6;
		if (piece instanceof Pawn) incrementBasedOnColor += 0;
		else if (piece instanceof Rook) incrementBasedOnColor += 1;
		else if (piece instanceof Knight) incrementBasedOnColor += 2;
		else if (piece instanceof Bishop) incrementBasedOnColor += 3;
		else if (piece instanceof Queen) incrementBasedOnColor += 4;
		else incrementBasedOnColor += 5;
		return incrementBasedOnColor;
	}

	public int[] getPiecesRemaining() {
		return piecesRemaining;
	}

	private void switchTurn() {
		turn = turn == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	public Color getWinner() {
		return winner;
	}

	public Color getTurn() { return turn; }

	public String toString(){
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i<7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + " \u3000 ";
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}

	public void castle(ChessPiece rook, ChessPiece king) throws IllegalMoveException {
		if (validateCastle(rook, king)) {
			int kingRow = king.getColor() == Color.WHITE ? 0 : 7;
			if (rook.column == 0 && queensideCastleIsPossible(kingRow)) {
				placePiece(rook, rowColToPosition(rook.row, king.column-1));
				placePiece(king, rowColToPosition(rook.row, rook.column-1));
				switchTurn();
			}
			else if (rook.column == 7 && kingsideCastleIsPossible(kingRow)) {
				placePiece(rook, rowColToPosition(rook.row, king.column+1));
				placePiece(king, rowColToPosition(rook.row, rook.column+1));
				switchTurn();
			}
			else throw new IllegalMoveException("Pieces are in the way of castling.");
		}
		else throw new IllegalMoveException("Illegal castle attempt.");
	}

	private String rowColToPosition(int row, int column) {
		char letter = (char) (column + 97);
		int newRow = row + 1;
		return letter + "" + newRow;
	}

	public boolean queensideCastleIsPossible(int kingRow) {
		return board[kingRow][1] == null && board[kingRow][2] == null && board[kingRow][3] == null;
	}

	public boolean kingsideCastleIsPossible(int kingRow) {
		return board[kingRow][5] == null && board[kingRow][6] == null;
	}

	private boolean validateCastle(ChessPiece rook, ChessPiece king) {
		boolean itsARook = rook instanceof Rook;
		boolean itsAKing = king instanceof King;
		boolean rookAndKingAreTheSameColor = rook.getColor() == king.getColor();
		boolean neitherHasMoved = !rook.hasMoved && !king.hasMoved;
		return itsAKing && itsARook && rookAndKingAreTheSameColor && neitherHasMoved;
	}


	private boolean moveIsDiagonal(String fromPostition, String toPosition){
		int[] rowColTo = boardRowCol(toPosition);
		int[] rowColFrom = boardRowCol(FromPosition);

		if(rowColTo[1] != rowColFrom[1]){
			return true;
		}
		return false;
	}

}
