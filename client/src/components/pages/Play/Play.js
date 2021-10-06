import React from "react";
import {
    Grid,
    makeStyles,
    Paper,
    Table,
    TableBody,
    TableContainer,
    TableRow,
    Typography
} from "@material-ui/core";
import {mockChessboard} from "./MockChessboard";
import Square from "./Square";

const useStyles = makeStyles({
    title: {
        margin: "10px",
    },
    board: {
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

    function getPosition(rowIndex, colIndex) {
        const indexArray = ['8', '7', '6', '5', '4', '3', '2', '1'];
        const letterArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
        return letterArray[colIndex] + indexArray[rowIndex];
    }

    function renderCells(row, rowIndex) {
        return (
            row.map((piece, index) => {
                const position = getPosition(rowIndex, index);
                return <Square key={index} piece={piece} position={position}/>
            })
        )
    }

    return <>
        <Typography className={classes.title} align="center">Play Page</Typography>
        <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
        >
            <TableContainer component={Paper} className={classes.board}>
                <Table>
                    <TableBody>
                        {renderRows()}
                    </TableBody>
                </Table>
            </TableContainer>
        </Grid>
    </>
}