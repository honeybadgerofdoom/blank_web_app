import React from "react";
import {Typography, Card, CardContent, CardMedia, Grid, Item} from "@material-ui/core";
import SetupRules from "./SetupRules";

export default function Rules(props) {
     return(
          <Grid container> 
            <Grid item xs={8}/>
            <Grid item>
            <Typography align="center" variant="h2">Rules for Elimination Chess</Typography>
            <br/>
            <Grid item>
            <generalRules.genRules/>
            <br/>
            <Grid item>
            <SetupRules/>
            </Grid>
            </Grid>
            </Grid>
        </Grid>
     );
}

const generalRules ={
    genRules: function (props){
         var rule1  = <Typography align="left" >Extinction Chess is a variant of actual chess.  You win the game by eliminating all of one piece of the opponent. This can be done by:  </Typography>
         var rule2 = <Typography display="block"> &emsp; ∙ Capturing the opponent’s  king,</Typography>
         var rule3 = <Typography display="block"> &emsp; ∙ Capturing the opponent’s queen,</Typography>
         var rule4 = <Typography display="block"> &emsp; ∙ Capturing both of the opponent's knights,</Typography>
         var rule5 = <Typography display="block"> &emsp; ∙ Capturing both of the opponent’s bishops,</Typography>
         var rule6 = <Typography display="block"> &emsp; ∙ Capturing both of the opponent's rooks,</Typography>
         var rule7 = <Typography display="block"> &emsp; ∙ Capturing all of the opponent’s pawns.</Typography>
         var rule8 = <Typography display="block"> &emsp; All player’s pieces can capture an opponent's piece by taking its position on the board.</Typography>
         var rule9 = <Typography display="block"> &emsp; The current legal moves of each piece will highlight once a piece is selected.  Below is an explanation of all the rules for each piece.</Typography>
          var combineRules = [rule1, rule2, rule3, rule4, rule4, rule5, rule6, rule7, rule8, rule9];
          return combineRules;
    }
}

