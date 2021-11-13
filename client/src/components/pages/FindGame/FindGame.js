import React from "react";
import { Grid } from "@material-ui/core";
import InvitesTable from "./InvitesTable";
import NewGamesTable from "./NewGamesTable";

export default function FindGame(props) {
    return (
        <Grid container justifyContent="center" >
            <Grid item>
                <InvitesTable userID={props.currentUserID} />
            </Grid>
            <Grid item>
                <NewGamesTable userID={props.currentUserID} />
            </Grid>
        </Grid>
    );
}