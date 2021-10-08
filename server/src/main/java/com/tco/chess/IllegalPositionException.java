package com.tco.chess;

public class IllegalPositionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //What the hecka?

	public IllegalPositionException(String message) {
		super(message);
	}

}
