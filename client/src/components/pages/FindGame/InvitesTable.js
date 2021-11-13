import React, {useEffect, useState} from "react";
import { Button, makeStyles, Paper, TableCell, TableRow, TextField, Container, ButtonGroup } from "@material-ui/core";
import {TableContent, TableControls} from "./findGameTables";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    root: {
        margin: "20px"
    },
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

    useEffect(() => {
        sendMyInvitesRequest(props.userID).then((newInvites) => {
            setAllInvites(newInvites);
            setFilteredInvites(newInvites)
        });
    }, []);

    function search(event) {
        const input = event.target.value;
        const matches = invites.filter(invite => searchForSender(invite, input));
        setFiltering(input !== "");
        setFilteredInvites(matches);
    }

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                <TableControls title="Join a Match from Invitations">
                    <TextField className={classes.search} variant="outlined" onChange={search} placeholder="Search my Invites..." />
                </TableControls>
                <TableContent headers={["Match ID", "Opponent", "Action"]}>
                    <MyInviteRows invites={invites} />
                </TableContent>
            </Paper>
        </Container>
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
            <TableCell align="right">
                <ButtonGroup variant="text">
                    <Button color="primary">Accept</Button>
                    <Button color="secondary">Decline</Button>
                </ButtonGroup>
            </TableCell>
        </TableRow>
    );
}