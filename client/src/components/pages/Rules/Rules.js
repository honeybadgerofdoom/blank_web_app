import React from "react";
import {Typography} from "@material-ui/core";

export default function Rules(props) {
     var rule = [Rule.OverAllRules.call(), Rule.King.call() ] ;

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
         <Typography> &emsp; &emsp; &emsp;  The current legal moves of each piece will highlight once a piece is selected.  
              Below is an explanation of all the rules for each piece. </Typography>
         <Typography display="block"> &emsp; &emsp; </Typography>
         <Typography display="block"> &emsp; &emsp; </Typography>
          </></>
          
          );
     },

     King: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> King</Typography></Typography>
     <Typography
          display="block">  <Typography align="center"> ____________________________________________________________________ </Typography></Typography> 
         <Typography display="block"> &emsp; </Typography>
          <Typography> &emsp; &emsp; &emsp;   </Typography>
          <Typography> &emsp; &emsp; &emsp; The king can move one square in any position whether that be horizontal, vertical, or diagonal unless the square is already occupied by your own piece.  </Typography>
         <Typography display="block"> &emsp; The king also has a special move, called castling.</Typography>
         <Typography display="block"> &emsp; To be able to accomplish this move, it requires that the king has never moved the entire game,</Typography>
         <Typography display="block"> &emsp;  and the player will also need to use one of their rooks that have also never moved.</Typography>
         <Typography display="block"> &emsp; The king will then move two squares towards the rook, or take the rook's spot. </Typography>
         <Typography display="block"> &emsp; The rook will then move one square in the opposite direction of the king or in between where the rook and king originally were. </Typography>
         <Typography display="block"> &emsp; </Typography>
          
          </></>
          
          );
     }
     
}