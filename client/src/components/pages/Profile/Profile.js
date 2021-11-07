import React from "react";
import {Typography} from "@material-ui/core";
import InvitesTable from "./InvitesTable";

export default function Profile(props) {
    // The current user's username is accessible via props.currentUser
    return (
        <>
            <Typography align="center">Profile Page</Typography>
            <InvitesTable userID={props.currentUserID} />
        </>
    )
}