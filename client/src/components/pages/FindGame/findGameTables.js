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

export function TableContent(props) {
    const classes = useStyles();
    return (
        <TableContainer component={Paper} className={classes.scrollable}>
            <Table>
                <TableHead>
                    <TableRow>
                        {props.headers.map((label, index) =>
                            <TableCell key={index} className={classes.headerText} align="center">
                                {label}
                            </TableCell>
                        )}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.children}
                </TableBody>
            </Table>
        </TableContainer>
    );
}