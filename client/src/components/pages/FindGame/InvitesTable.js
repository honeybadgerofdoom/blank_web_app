import React, {useEffect, useState} from "react";
import { Button, makeStyles, Paper, TableCell, TableRow, TextField, ButtonGroup } from "@material-ui/core";
import {TableContent, TableControls} from "./findGameTables";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    search: {
        width: "100%",
    },
})

export default function InvitesTable(props) {
    const classes = useStyles();
    const [allInvites, setAllInvites] = useState([]);
    const [filteredInvites, setFilteredInvites] = useState([]);
    const [filtering, setFiltering] = useState(false);

    const invites = filtering ? filteredInvites : allInvites;

    useEffect(refreshInvites, []);

    function refreshInvites() {
        sendMyInvitesRequest(props.userID).then(newInvites => {
            setAllInvites(newInvites);
            setFilteredInvites(newInvites)
        });
    }

    function search(event) {
        const input = event.target.value;
        const matches = invites.filter(invite => searchForSender(invite, input));
        setFiltering(input !== "");
        setFilteredInvites(matches);
    }

    return (
        <Paper elevation={3}>
            <TableControls title="Join a Match from Invitations">
                <TextField className={classes.search} size="small" variant="outlined" onChange={search} placeholder="Search my Invites..." />
            </TableControls>
            <TableContent headers={["Match ID", "Opponent", "Action"]}>
                <MyInviteRows {...props} invites={invites} refreshInvites={refreshInvites} />
            </TableContent>
        </Paper>
    );
}

async function sendMyInvitesRequest(userID) {
    const response = await sendRequest({ requestType: "myInvites", userID: userID });
    if (!response) {
        console.log("Error with myInvites request")
        return [];
    }
    //console.log({ myInvitesResponse: response });
    return response.invites;
}

function searchForSender(invite, input) {
    for (let i = 0; i < input.length; i++) {
        if (invite.sender.charAt(i) !== input.charAt(i)) {
            return false;
        }
    }
    return true;
}

function MyInviteRows(props) {
    return props.invites.map((invite, index) =>
        <TableRow key={index}>
            <TableCell align="center">{invite.gameID}</TableCell>
            <TableCell align="center">{invite.sender}</TableCell>
            <TableCell align="center">
                <ButtonGroup variant="text">
                    <Button color="primary" onClick={() => acceptInvite(invite, props)}>
                        Accept
                    </Button>
                    <Button color="secondary" onClick={() => declineInvite(invite, props)}>
                        Decline
                    </Button>
                </ButtonGroup>
            </TableCell>
        </TableRow>
    );
}

async function modifyInviteRequest(requestBody, successMsg, errorMsg, props) {
    const response = await sendRequest(requestBody);
    if (response && response.success) {
        props.showMessage(successMsg, "success");
        props.refreshInvites();
    } else {
        props.showMessage(errorMsg, "error");
    }
}

async function acceptInvite(invite, props) {
    const requestBody = { requestType: "acceptInvite",  gameID: invite.gameID, sender: invite.sender, player2: props.userID };
    const success = `Accepted invite from ${invite.sender}!`;
    const error = "Failed to accept invite.";
    await modifyInviteRequest(requestBody, success, error, props);
}

async function declineInvite(invite, props) {
    const success = `Declined invite from ${invite.sender}.`;
    const error = "Failed to decline invite."
    const requestBody = { requestType: "declineInvite", gameID: invite.gameID, sender: invite.sender, receiver: props.userID };
    await modifyInviteRequest(requestBody, success, error, props);
}