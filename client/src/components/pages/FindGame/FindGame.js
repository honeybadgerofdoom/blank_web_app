import React from "react";
import { Grid } from "@material-ui/core";
import InvitesTable from "./InvitesTable";
import NewGamesTable from "./NewGamesTable";

export default function FindGame(props) {
    return (
        <Grid container justifyContent="center" >
            <Grid item xs={12} md={5}>
                <NewGamesTable userID={props.currentUserID} />
            </Grid>
            <Grid item xs={12} md={5}>
                <InvitesTable userID={props.currentUserID} />
            </Grid>
        </Grid>
    );
}