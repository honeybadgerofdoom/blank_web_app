import React, {useEffect, useState} from "react";
import {
    Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableRow, TableHead, TextField, Container
} from "@material-ui/core";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    root: {
        margin: "20px",
    },
    scrollable: {
        overflow: "auto",
        maxHeight: "50vh",
    },
    headerText: {
        fontWeight: "bold",
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
    console.log({invites})

    useEffect(() => {
        sendMyInvitesRequest();
    }, [])

    async function sendMyInvitesRequest() {
        const response = await sendRequest({requestType: "myInvites", userID: props.userID});
        if(response) {
            console.log({response})
            setAllInvites(response.invites);
            setFilteredInvites(response.invites)
        }
        else {
            console.log("Error with myInvites request")
        }
    }

    function search(event) {
        const input = event.target.value;
        setFiltering(input !== "");
        const matches = invites.filter((invite) => searchForSender(invite, input));
        setFilteredInvites(matches);
    }

    function searchForSender(invite, input) {
        for(let i = 0; i < input.length; i++) {
            if(invite.sender.charAt(i) !== input.charAt(i)) return false;
        }
        return true;
    }

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                {TableControls()}
                <TableContainer component={Paper} className={classes.scrollable}>
                    <Table>
                        <TableBody>
                            {invites.map((invite, index) => {
                                return (
                                    <TableRow key={index}>
                                        <TableCell>{invite.sender}</TableCell>
                                        <TableCell align="right">{invite.gameID}</TableCell>
                                        <TableCell align="right"><Button color="primary">Accept</Button></TableCell>
                                        <TableCell align="right"><Button color="secondary">Decline</Button></TableCell>
                                    </TableRow>
                                )
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>
        </Container>
    )

    function TableControls() {
        return (
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell colSpan={5} align="center">
                                <TextField className={classes.search} variant="outlined" onChange={search} placeholder="Search My Invites..."/>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell className={classes.headerText}>Opponent</TableCell>
                            <TableCell className={classes.headerText} align="left">Game ID</TableCell>
                            <TableCell className={classes.headerText} align="left">Accept</TableCell>
                            <TableCell className={classes.headerText} align="center">Decline</TableCell>
                        </TableRow>
                    </TableHead>
                </Table>
            </TableContainer>
        )
    }

}