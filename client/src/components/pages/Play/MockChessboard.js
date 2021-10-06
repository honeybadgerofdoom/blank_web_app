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

export const mockChessboard = {
    row1: {
        a1: pieces.white.rook,
        b1: pieces.white.knight,
        c1: pieces.white.bishop,
        d1: pieces.white.queen,
        e1: pieces.white.king,
        f1: pieces.white.bishop,
        g1: pieces.white.rook,
        h1: pieces.white.knight,
    },
    row2: {
        a2: pieces.white.pawn,
        b2: pieces.white.pawn,
        c2: pieces.white.pawn,
        d2: pieces.white.pawn,
        e2: pieces.white.pawn,
        f2: pieces.white.pawn,
        g2: pieces.white.pawn,
        h2: pieces.white.pawn,
    },
    row3: {
        a3: pieces.empty,
        b3: pieces.empty,
        c3: pieces.empty,
        d3: pieces.empty,
        e3: pieces.empty,
        f3: pieces.empty,
        g3: pieces.empty,
        h3: pieces.empty,
    },
    row4: {
        a4: pieces.empty,
        b4: pieces.empty,
        c4: pieces.empty,
        d4: pieces.empty,
        e4: pieces.empty,
        f4: pieces.empty,
        g4: pieces.empty,
        h4: pieces.empty,
    },
    row5: {
        a5: pieces.empty,
        b5: pieces.empty,
        c5: pieces.empty,
        d5: pieces.empty,
        e5: pieces.empty,
        f5: pieces.empty,
        g5: pieces.empty,
        h5: pieces.empty,
    },
    row6: {
        a6: pieces.empty,
        b6: pieces.empty,
        c6: pieces.empty,
        d6: pieces.empty,
        e6: pieces.empty,
        f6: pieces.empty,
        g6: pieces.empty,
        h6: pieces.empty,
    },
    row7: {
        a7: pieces.black.pawn,
        b7: pieces.black.pawn,
        c7: pieces.black.pawn,
        d7: pieces.black.pawn,
        e7: pieces.black.pawn,
        f7: pieces.black.pawn,
        g7: pieces.black.pawn,
        h7: pieces.black.pawn,
    },
    row8: {
        a8: pieces.black.rook,
        b8: pieces.black.knight,
        c8: pieces.black.bishop,
        d8: pieces.black.queen,
        e8: pieces.black.king,
        f8: pieces.black.bishop,
        g8: pieces.black.rook,
        h8: pieces.black.knight,
    },
};

export const chessRows = createRows();

function createRows() {
    let tempRows = [];
    for(const [key, value] of Object.entries(mockChessboard)) {
        tempRows.push(value)
    }
    return tempRows;
}