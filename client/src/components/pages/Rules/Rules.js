import React from "react";
import {Typography, Card, CardContent, CardMedia, Grid, Item} from "@material-ui/core";
import SetupRules from "./SetupRules";

export default function Rules(props) {
     return(
          <Grid container justifyContent="center">
            <Grid item xs={8}/>
            <Grid item>
            <Typography align="center" variant="h2">Rules for Elimination Chess</Typography>
            <br/>
            <Grid item>
            <GeneralRules />
            <br/>
            <Grid item>
            <SetupRules/>
            </Grid>
            </Grid>
            </Grid>
        </Grid>
     );
}


    function GeneralRules(){
          return (
               <Card style={{ width: '73rem' }}>
                    <CardContent>
                    <div>
                     <Typography align="left" >Extinction Chess is a variant of actual chess.  You win the game by eliminating all of one piece of the opponent. This can be done by:  </Typography>
                     <Typography display="block"> &emsp;∙ Capturing the opponent’s  king,</Typography>
                     <Typography display="block"> &emsp; ∙ Capturing the opponent’s queen,</Typography>
                     <Typography display="block"> &emsp; ∙ Capturing both of the opponent's knights,</Typography>
                     <Typography display="block"> &emsp; ∙ Capturing both of the opponent’s bishops,</Typography>
                     <Typography display="block"> &emsp; ∙ Capturing both of the opponent's rooks,</Typography>
                     <Typography display="block"> &emsp; ∙ Capturing all of the opponent’s pawns.</Typography>
                     <Typography display="block"> All player’s pieces can capture an opponent's piece by taking its position on the board.</Typography>
                     <Typography display="block"> The current legal moves of each piece will highlight once a piece is selected.  Below is an explanation of all the rules for each piece.</Typography>
                    </div>
                    </CardContent>
               </Card>           
          );
    }


