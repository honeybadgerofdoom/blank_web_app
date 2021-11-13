import React from "react";
import {
    makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableRow, TableHead
} from "@material-ui/core";

const useStyles = makeStyles( {
    scrollable: {
        overflow: "auto",
        maxHeight: "50vh"
    },
    headerText: {
        fontWeight: "bold",
    }
})

export function TableControls(props) {
    const classes = useStyles();
    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell className={classes.headerText} align="center">{props.title}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell colSpan={5} align="center">
                            {props.children}
                        </TableCell>
                    </TableRow>
                </TableHead>
            </Table>
        </TableContainer>
    );
}
