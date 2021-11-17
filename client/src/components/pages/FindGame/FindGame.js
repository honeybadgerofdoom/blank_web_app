import React from "react";
import { Grid } from "@material-ui/core";
import InvitesTable from "./InvitesTable";
import NewGamesTable from "./NewGamesTable";

export default function FindGame(props) {
    function TableGridItem(props) {
        return (
            <Grid item xs={12} sm={10} md={5} lg={4}>
                {props.children}
            </Grid>
        );
    }

    return (
        <Grid className="mt-1" container justifyContent="center" spacing={6}>
            <TableGridItem>
                <NewGamesTable userID={props.currentUserID} showMessage={props.showMessage} />
            </TableGridItem>
            <TableGridItem>
                <InvitesTable userID={props.currentUserID} showMessage={props.showMessage} />
            </TableGridItem>
        </Grid>
    );
}