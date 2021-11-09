import React from "react";
import {Container, makeStyles, Paper, Table, TableBody, TableCell, TableHead, TableRow} from "@material-ui/core";

const useStyles = makeStyles( {
    root: {
        minWidth: "25vw",
    },
    paper: {
        width: "100%",
        margin: "20px",
        padding: "20px",
    },
});

export default function MyStats(props) {
    const classes = useStyles();

    return (
        <Container maxWidth="sm" className={classes.root}>
            <Paper elevation={3} className={classes.paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center">Wins</TableCell>
                            <TableCell align="center">Losses</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell align="center">5</TableCell>
                            <TableCell align="center">7</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    )
}