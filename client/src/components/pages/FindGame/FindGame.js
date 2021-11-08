import React from "react";
import InvitesTable from "./InvitesTable";

export default function FindGame(props) {
    return <InvitesTable userID={props.currentUserID} showMessage={props.showMessage} />
}