import React, {useEffect, useState} from "react";
import {Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableRow, TableHead
} from "@material-ui/core";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    root: {
        width: "40vw",
        margin: "20px",
    },
    scrollable: {
        overflow: "auto",
        maxHeight: "25vw",
    },
    header: {
        fontWeight: "bold",
    },
})

export default function InvitesTable(props) {
    const classes = useStyles();
    const [invites, setInvites] = useState([]);

    useEffect(() => {
        sendMyInvitesRequest();
    })

    async function sendMyInvitesRequest() {
        const response = await sendRequest({requestType: "myInvites", userID: props.userID});
        if(response) {
            setInvites(response.invites);
        }
        else {
            console.log("Error with myInvites request")
        }
    }

    return (
        <TableContainer component={Paper} className={classes.root}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell className={classes.header}>Opponent</TableCell>
                        <TableCell className={classes.header} align="right">Status</TableCell>
                        <TableCell className={classes.header} align="right">Game ID</TableCell>
                        <TableCell className={classes.header} align="right">Accept</TableCell>
                        <TableCell className={classes.header} align="right">Decline</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className={classes.scrollable}>
                    {invites.map((invite, index) => {
                        return (
                            <TableRow key={index}>
                                <TableCell>{invite.sender}</TableCell>
                                <TableCell align="right">{invite.status}</TableCell>
                                <TableCell align="right">{invite.gameID}</TableCell>
                                <TableCell align="right"><Button>Accept</Button></TableCell>
                                <TableCell align="right"><Button>Decline</Button></TableCell>
                            </TableRow>
                        )
                    })}
                </TableBody>
            </Table>
        </TableContainer>
    )

}