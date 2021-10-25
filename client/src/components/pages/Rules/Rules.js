import React from "react";
import {Typography} from "@material-ui/core";

export default function Rules(props) {
     var rule = [Rule.OverAllRules.call(), Rule.Invite.call() ] ;

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
              Below is an explanation of all the rules for each piece as well as general rules for  </Typography>
              <Typography display="block"> &emsp; the game. </Typography>
         <Typography display="block"> &emsp; &emsp; </Typography>
          </></>
          
          );
     },

     Invite: function (props){
          return( 
          <><>
          <Typography display="block"> &emsp; </Typography>
          <Typography align="center"><Typography variant="h3"> Inviting someone to a game </Typography></Typography>
     <Typography
          display="block">  <Typography align="center"> ____________________________________________________________________ </Typography></Typography> 
         <Typography display="block"> &emsp; </Typography>
          <Typography> &emsp; &emsp; &emsp; Well add once we add invitations as a game option  </Typography>
         <Typography display="block"> &emsp; </Typography>
          </></>
          
          );
     }
     
}