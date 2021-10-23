package com.tco.chess;

import java.util.ArrayList;
import java.lang.StringBuilder;

public abstract class ChessPiece {
	public enum Color {WHITE,BLACK};
	protected ChessBoard board = null;
	protected int row;
	protected int column;
	protected Color color;
	protected boolean hasMoved = false; // This is only used for Rook and King, for castling purposes.
	protected int numberOfMoves = 0; //This is only used in Pawn for En Passant
	
	public ChessPiece(ChessBoard board, Color color) {
		this.board = board;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
		
	}
	
	public String getPosition() {
		char row = (char)(this.row + '1');
		char column = (char)(this.column + 'a');

		StringBuilder sb = new StringBuilder("");
		String str = sb.append(column).append(row).toString();
		
		return str;
		
	}
	
	public void setPosition(String position) throws IllegalPositionException{
		int[] rowCol = ChessBoard.boardRowCol(position);
		this.row = rowCol[0];
		this.column = rowCol[1];
		
	}

	protected String rowColToPosition(int row, int column) {
		char letter = (char) (column + 97);
		int newRow = row + 1;
		return letter + "" + newRow;
	}

	private char colToCharacter(int col) {
		char letter = (char) (col + 97);
		return letter;
	}
	
	abstract public String toString();
	
	abstract public ArrayList<String> legalMoves();
}
