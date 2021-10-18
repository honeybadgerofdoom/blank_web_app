package com.tco.chess;

public class IllegalPromotionException extends Exception {

    private static final long serialVersionUID = 1L;

    public IllegalPromotionException(String message) {
        super(message);
    }

}
