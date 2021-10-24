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
         <Typography display="block">  </Typography>
         <Typography display="block">  </Typography>
         <Typography display="block">  </Typography>
          <Typography display="block"> </Typography>
          <Typography> &emsp; &emsp; &emsp;Extinction Chess is a variant of actual chess.  You win the game by eliminating all of one piece of the opponent.  This can be done by:  </Typography>
         <Typography display="block"> &emsp; &emsp; &emsp; &emsp;∙ Capturing the opponent’s  king, </Typography>
         <Typography display="block">  </Typography>


          </></>
          
          );
     }
}