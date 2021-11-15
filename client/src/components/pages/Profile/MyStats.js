import React, {useEffect, useState} from "react";
import {Container, makeStyles, Paper, Table, TableBody, TableCell, TableHead, TableRow} from "@material-ui/core";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    root: {
        minWidth: "25vw",
    },
    paper: {
        width: "100%",
        margin: "20px",
        padding: "20px",
    },
    bold: {
        fontWeight: "bold",
    },
});

export default function MyStats(props) {
    const classes = useStyles();
    const [wins, setWins] = useState(0);
    const [losses, setLosses] = useState(0);

    useEffect(() => {
        sendStatsRequest();
    }, []);

    async function sendStatsRequest() {
        const response = await sendRequest({requestType: "stats", userID: props.currentUserID});
        if(response) {
            setWins(response.wins);
            setLosses(response.losses);
        }
        else {
            console.log("Error sending Stats request");
        }
    }

    return (
        <Container maxWidth="sm" className={classes.root}>
            <Paper elevation={3} className={classes.paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center" className={classes.bold}>Wins</TableCell>
                            <TableCell align="center" className={classes.bold}>Losses</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell align="center">{wins}</TableCell>
                            <TableCell align="center">{losses}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    )
}