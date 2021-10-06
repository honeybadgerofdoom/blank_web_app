import Square from "./Square";
import React from "react";

const pieces = {
    white: {
        king: "\u2654",
        queen: "\u2655",
        rook: "\u2656",
        bishop: "\u2657",
        knight: "\u2658",
        pawn: "\u2659"
    },
    black: {
        king: "\u265A",
        queen: "\u265B",
        rook: "\u265C",
        bishop: "\u265D",
        knight: "\u265E",
        pawn: "\u265F"
    },
    empty: "\u1806"
}

export const mockChessboard = [
    //String array, order on the client is implied
    [pieces.white.rook,
    pieces.white.knight,
    pieces.white.bishop,
    pieces.white.queen,
    pieces.white.king,
    pieces.white.bishop,
    pieces.white.rook,
    pieces.white.knight,],

    [pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,],

    [pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,],

    [pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,],

    [pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,],

    [pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,],

    [pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,],

    [pieces.black.rook,
    pieces.black.knight,
    pieces.black.bishop,
    pieces.black.queen,
    pieces.black.king,
    pieces.black.bishop,
    pieces.black.rook,
    pieces.black.knight,],
];