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
    bold: {
        fontWeight: "bold",
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
                            <TableCell align="center" className={classes.bold}>Wins</TableCell>
                            <TableCell align="center" className={classes.bold}>Losses</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell align="center">-</TableCell>
                            <TableCell align="center">-</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    )
}