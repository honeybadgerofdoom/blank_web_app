import React from "react";
import {Typography, Card, CardContent, CardMedia, Grid, Item} from "@material-ui/core";
import ruleSetup from "SetupRules";

export default function Rules(props) {
     return(
          <Grid container> 
            <Grid item xs={10}/>
            <Grid item>
            <Typography align="center" variant="h2">Rules for Elimination Chess</Typography>

            <br/>
            <Grid item>
            {props.SetupRules}

            </Grid>
            </Grid>
        </Grid>
     );
}