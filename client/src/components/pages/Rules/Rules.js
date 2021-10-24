import React from "react";
import {Typography} from "@material-ui/core";

export default function Rules(props) {
     var combine = OverAllRules.OverAllRules.call();
     return combine;
}

const OverAllRules= {
     OverAllRules: function (){
          return( <><><Typography align="center"><Typography variant="h3"> Rules for Elimination Chess </Typography></Typography>
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
         <Typography display="block"> &emsp; </Typography>
          </></>
          
          );
     }
}