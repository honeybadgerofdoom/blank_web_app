import React from "react";

export default function SetupRules(props) {
    return(

    );
}

const ruleSetup= {
     generalRules = ["Extinction Chess is a variant of actual chess.  You win the game by eliminating all of one piece of the opponent." +
     " This can be done by: \r∙ Capturing the opponent’s  king, \r∙ Capturing the opponent’s queen, \r∙ Capturing both of the opponent's" +
     " knights, \r∙ Capturing both of the opponent’s bishops, \r∙ Capturing both of the opponent's rooks,\r∙ Capturing all of the "+
     "opponent’s pawns. \rAll player’s pieces can capture an opponent's piece by taking its position on the board.\rThe current legal moves"+
     + " of each piece will highlight once a piece is selected.  \rBelow is an explanation of all the rules for each piece."],
     
     headings = ["Pawn", "Knight", "Bishop", "Rook", "Queen", "King"],

     pieceRules = ["The pawn is the only piece that cannot move backwards. When a pawn initially moves, it has the option to move" +
     " either one or two squares forward, or capture a piece in a diagonal square.  A pawn cannot jump over a piece whether friendly" +
      "or opponent’s.  After the initial move, a pawn can only move one square. \r forward, but can still capture an opponent’s piece" +
      " in a diagonal.Pawn’s are capable of promotion. If a pawn advances all the way to the opposite end of the board, it is promoted" + 
      " to another piece of the player’s choosing. This includes, king, queen, knight, bishop, or rook. The piece will immediately, " +
      "before the end of turn, be replaced to the new piece.  There is no limit to which  piece you can promote to,  even if it’s always" +
      " the same one.  The last special move a pawn can do is called en passant.  It can only be done after the initial move of the opponent’s" +
      " pawn, when it moves two squares vertically and it could have been captured if it had only advanced one square.    On the very next turn," + 
      "after the initial move, the player can place their pawn diagonal, as if the piece were there, and capture the opponent’s pawn.", 
      "The knight is unique in that it has the ability to jump over pieces.  It can move two squares vertically, in either direction, and one" + 
      " square horizontally, in any direction. The order of this doesn’t matter, However, it must move both vertically and  horizontally if" + 
      " piece is played.",
      "The bishop can move as far as it wants but only on the dianolgals and only if the squares inbetween from start position to finish" +
      "position are unoccupied. ",
      "The rook can move as far as it wants but only on the diagonals and only if the squares inbetween from start position to finish" +
      " position are unoccupied. ",
      "The queen, a very powerful piece in the game, moves like a bishop and a rook.  It is allowed to move any number of unoccupied squares vertically, ",
      "The king can move one square in any position whether that be horizontally, vertically, or diagonally unless the square is already occupied by your" + 
      " own piece. The king also has a special move, called castling.  To be able to accomplish this move, it requires that the king has never moved the " +
      "entire game, and the player will also need to use one of their rooks that has also never moved.  The king will then take the rook's spot.   The " + 
      "rook will  then move in the opposite direction  of the king, jumping over the king, and take the position next to the king.   Keep in mind to " + 
      "complete this, there must not  be a piece inbetween the rook and the king, whether friendly, or the enemies.  Also, if you want to castle, " + 
      "the king must be moved first."
     ]
}

