import React from "react";
import {Typography, Grid, Container, makeStyles} from "@material-ui/core";
import { GeneralRules, SetupRules } from "./RulesCards";

const gridSpacing = 5;

const useStyles = makeStyles({
    root: {
        padding: (gridSpacing * 8) / 2  // fix issue with Grid spacing creating scrollbar
    }
});

export default function Rules() {
    const classes = useStyles();
     return (
         <Container className={classes.root}>
             <Typography align="center" variant="h2">Extinction Chess</Typography>
             <br/>
             <Grid container justifyContent="center" alignContent="center" spacing={gridSpacing}>
                 <GeneralRules />
                 <SetupRules/>
             </Grid>
         </Container>
     );
}
