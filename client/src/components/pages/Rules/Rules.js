import React from "react";
import {Typography} from "@material-ui/core";

export default function Rules(props) {
     return( <><><Typography align="center"><Typography variant="h3"> Rules for Elimination Chess </Typography></Typography>
     <Typography
          display="block">  <Typography align="center"> ____________________________________________________________________ </Typography></Typography> 
         <Typography display="block">  </Typography>
         <Typography display="block">  </Typography>
         <Typography display="block">  </Typography>
         <Typography paragraph={true}>  Extinction Chess is a variant of actual chess.  You win the game by eliminating all of one piece of the opponent.  This can be done by: </Typography>
        <Typography display="block"> ∙ Capturing the opponent’s  king, </Typography>
         <Typography display="block">  </Typography>


          </></>
          
     );
}