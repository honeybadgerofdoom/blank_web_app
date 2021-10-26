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
         <Typography display="block"> &emsp; &emsp; All player’s pieces can capture an opponent's piece by taking its position on the board.</Typography>
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
          <Typography> &emsp; &emsp; &emsp; The knight is unique in that it has the ability to jump over pieces.  It can move two squares 
          vertically, in either direction, and one square horizontally,</Typography>
         <Typography display="block"> &emsp;  in any direction. The order of this doesn’t matter. 
          </Typography>
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
         <Typography display="block"> &emsp;  in any direction. The order of this doesn’t matter. 
          </Typography>
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
          <Typography> &emsp; &emsp; &emsp; The queen, a very powerful piece in the game, and moves like a bishop and a rook.  It is allowed to 
          move any number of unoccupied squares vertically,   </Typography>
         <Typography display="block"> &emsp; horizontally, and diagonally. Because the queen is very powerful, most pawns are promoted to a
          queen.  </Typography>
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
         <Typography display="block"> &emsp;   will also need to use one of their rooks that have also never moved.  The king will 
         then move two squares towards the rook, or take the rook's spot.   The rook will </Typography>
         <Typography display="block"> &emsp;  then move one square in the opposite direction of the king or in between where the rook 
         and king originally were.  Keep in mind to complete this, there must not  </Typography>
         <Typography display="block"> &emsp; be a piece inbetween the rook and the king, whether friendly, or the enemies.  Additional 
         rules to castling are, the king must not be currently in check and the  </Typography>
         <Typography display="block"> &emsp; move does not put the king in check.</Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     }
     
}