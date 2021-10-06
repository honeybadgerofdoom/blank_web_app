import React from "react";
import {Grid, makeStyles, Paper, Table, TableBody, TableContainer, TableRow,} from "@material-ui/core";
import {mockChessboard} from "./MockChessboard";
import Square from "./Square";

const useStyles = makeStyles({
    root: {
        width: "80vw",
    },
});

export default function Play() {
    const classes = useStyles();

    function renderRows() {
        return (
            mockChessboard.reverse().map((row, index) => {
                return (<TableRow key={index}>{renderCells(row, index)}</TableRow>);
            })
        )
    }

    function renderCells(row, rowIndex) {
        return (
            row.map((piece, index) => {
                const position = getPosition(rowIndex, index);
                return <Square key={index} piece={piece} position={position}/>
            })
        )
    }

    function getPosition(rowIndex, colIndex) {
        const indexArray = ['8', '7', '6', '5', '4', '3', '2', '1'];
        const letterArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
        return letterArray[colIndex] + indexArray[rowIndex];
    }

    return <>
        <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
        >
            <TableContainer component={Paper} className={classes.root}>
                <Table>
                    <TableBody>
                        {renderRows()}
                    </TableBody>
                </Table>
            </TableContainer>
        </Grid>
    </>
}