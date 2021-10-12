package com.tco.chess;

import java.util.ArrayList;
import java.lang.StringBuilder;

public abstract class ChessPiece {
	public enum Color {WHITE,BLACK};
	protected ChessBoard board = null;
	protected int row;
	protected int column;
	protected Color color;
	
	//Helper Methods
	
//	private boolean validatePosition(String position) {
//		if(position.length() != 2) {
//			return false;
//		}
//		if(!(position.charAt(0) >= 'a' && position.charAt(0) <= 'h' )) {
//			return false;
//		}
//		if(!(position.charAt(1) >= '1' && position.charAt(1) <= '8')) {
//			return false;
//		}
//		return true;
//	}
	
	private int[] boardRowCol(String position) throws IllegalPositionException {
		
		if(ChessBoard.validatePosition(position)) {
			int[] arr = new int[2];
			int column = position.charAt(0) % 'a'; // always sets the position to a1
			int row = position.charAt(1) % '1'; 
			
			arr[0] = row;
			arr[1] = column;
			return arr;
			
		}
		throw new IllegalPositionException("Error in boardRowCol");
	}
	// Helper Methods
	
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
		int[] rowCol = boardRowCol(position);
		this.row = rowCol[0];
		this.column = rowCol[1];
		
	}
	
	abstract public String toString();
	
	abstract public ArrayList<String> legalMoves();
}
