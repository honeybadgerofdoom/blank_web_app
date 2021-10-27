import React from "react";
import {Typography} from "@material-ui/core";

export default function Rules(props) {
     var rule = [Rule.OverAllRules.call(), Rule.Pawn.call(), Rule.Knight.call(), 
          Rule.Bishop.call(), Rule.Rook.call(), Rule.Queen.call(), Rule.King.call() ] ;

     return rule;
     
}


const Rule= {
     OverAllRules: function (props){
          return( <><><Typography align="center"><Typography variant="h2"> Rules for Elimination Chess </Typography></Typography>
     <Typography
          display="block">  <Typography align="center"> ____________________________________________________________________ </Typography></Typography> 
         <Typography display="block"> &emsp; </Typography>
          <Typography> &emsp; &emsp; &emsp; Extinction Chess is a variant of actual chess.  You win the game by eliminating all of one piece of the opponent.  
               This can be done by:  </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp; &emsp;∙ Capturing the opponent’s  king, </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp; &emsp;∙ Capturing the opponent’s queen, </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp; &emsp;∙ Capturing both of the opponent's knights, </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp; &emsp;∙ Capturing both of the opponent’s bishops, </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp; &emsp;∙ Capturing both of the opponent's rooks, </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp; &emsp;∙ Capturing all of the opponent’s pawns. </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; All player’s pieces can capture an opponent's piece by taking its position on the board.</Typography>
         <Typography> &emsp; &emsp; &emsp;  The current legal moves of each piece will highlight once a piece is selected.  
              Below is an explanation of all the rules for each piece. </Typography>
         <Typography display="block"> &emsp; &emsp; </Typography>
         <Typography display="block"> &emsp; &emsp; </Typography>
          </></>
          
          );
     },

     Pawn: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> Pawn </Typography></Typography>
          <Typography display="block">  <Typography align="center"> ____________________________________________________________________ 
          </Typography></Typography> 
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The pawn is the only piece that cannot move backwards. When a pawn initially moves, it has the  
          option to move either one or two squares forward, or   </Typography>
         <Typography display="block"> &emsp;  capture a piece in a diagonal square.  A pawn cannot jump over a piece whether friendly or
          opponent’s.  After the initial move, a pawn can only move one square </Typography>
         <Typography display="block"> &emsp;  forward, but can still capture an opponent’s piece in a diagonal.</Typography>
         <Typography display="block"> &emsp; </Typography>
          <Typography> &emsp; &emsp; &emsp; Pawn’s are capable of promotion. If a pawn advances all the way to the opposite end of the board, it is  
          promoted to another piece of the player’s choosing. This  </Typography> 
          <Typography display="block"> &emsp;  includes, king, queen, knight, bishop, or rook. The piece will immediately, 
          before the end of turn, be replaced to the new piece.  There is no limit to which  piece you </Typography>
          <Typography display="block"> &emsp;    can promote to,  even if it’s always the same one. </Typography>
          <Typography display="block"> &emsp; </Typography>
          <Typography> &emsp; &emsp; &emsp; The last special move a pawn can do is called en passant.  It can only be done after the initial move of the 
          opponent’s pawn, when it moves two squares 
          </Typography>
          <Typography display="block"> &emsp;  vertically and it could have been captured if it had only advanced one square.    On the very next turn,
          after the initial move, the player can place their pawn diagonal, </Typography>
         <Typography display="block"> &emsp; as if the piece were there, and capture the opponent’s pawn.</Typography>
         <Typography display="block"> &emsp; </Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     },

     Knight: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> Knight </Typography></Typography>
          <Typography display="block">  <Typography align="center"> ____________________________________________________________________ 
          </Typography></Typography> 
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The knight is unique in that it has the ability to jump over pieces.  It can move two squares 
          vertically, in either direction, and one square horizontally,</Typography>
         <Typography display="block"> &emsp;  in any direction. The order of this doesn’t matter, However, it must move both vertically and 
         horizontally if piece is played. </Typography>
         <Typography display="block"> &emsp; </Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     },

     Bishop: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> Bishop</Typography></Typography>
          <Typography display="block">  <Typography align="center"> ____________________________________________________________________ 
          </Typography></Typography> 
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The bishop can move as far as it wants but only on the dianolgals and only if the squares inbetween  
          from start position to finish position are unoccupied.  </Typography>
         <Typography display="block"> &emsp; There are two bishops per player.  They start on opposite color squares, so their diagonals that they can 
         occupy will always be different.  </Typography>
         <Typography display="block"> &emsp; </Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     },

     Rook: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> Rook</Typography></Typography>
          <Typography display="block">  <Typography align="center"> ____________________________________________________________________ 
          </Typography></Typography> 
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The rook can move as far as it wants but only on the diagonals and only if the squares inbetween  
          from start position to finish position are unoccupied.  </Typography>
          <Typography display="block"> &emsp; There are two rooks for each player that start on opposite colors of the board. </Typography>
         <Typography display="block"> &emsp; </Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     },

     Queen: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> Queen</Typography></Typography>
          <Typography display="block">  <Typography align="center"> ____________________________________________________________________ 
          </Typography></Typography> 
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The queen, a very powerful piece in the game, moves like a bishop and a rook.  It is allowed to 
          move any number of unoccupied squares vertically,   </Typography>
         <Typography display="block"> &emsp; horizontally, or diagonally. The squares inbetween start and finish position must be unoccupied.  
         Because the queen is very powerful, most pawns are promoted  </Typography>
         <Typography display="block"> &emsp;  to a queen. </Typography>
         <Typography display="block"> &emsp; </Typography>
         <Typography display="block"> &emsp; </Typography>
          </></>
          
          );
     },

     King: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> King</Typography></Typography>
          <Typography display="block">  <Typography align="center"> ____________________________________________________________________ 
          </Typography></Typography> 
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The king can move one square in any position whether that be horizontally, vertically, 
               or diagonally unless the square is already occupied by your own piece.  </Typography>
         <Typography display="block"> &emsp; The king also has a special move, called castling.  To be able to accomplish this move, 
         it requires that the king has never moved the entire game, and the player </Typography>
         <Typography display="block"> &emsp;   will also need to use one of their rooks that has also never moved.  The king will 
         then take the rook's spot.   The rook will  then move in the opposite direction  of the  </Typography>
         <Typography display="block"> &emsp; king, jumping over the king, and take the position next to the king.   Keep in mind 
         to complete this, there must not  be a piece inbetween the rook and the king,</Typography>
         <Typography display="block"> &emsp;   whether friendly, or the enemies.  
         Also, if you want to castle, the king must be moved first. </Typography> 
         <Typography display="block"> &emsp;  </Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     }
     
}