import React from "react";
import { Grid } from "@material-ui/core";
import InvitesTable from "./InvitesTable";
import NewGamesTable from "./NewGamesTable";

export default function FindGame(props) {
    return (
        <Grid className="mt-1" container justifyContent="center" spacing={6}>
            <Grid item xs={12} sm={10} md={5} lg={4}>
                <NewGamesTable userID={props.currentUserID} />
            </Grid>
            <Grid item xs={12} sm={10} md={5} lg={4}>
                <InvitesTable userID={props.currentUserID} />
            </Grid>
        </Grid>
    );
}