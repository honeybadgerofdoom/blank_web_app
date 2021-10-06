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
import {chessRows} from "./MockChessboard";
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
        let rows = []
        chessRows.reverse().forEach((row, index) => {
            rows.push(<TableRow key={index}>{renderCells(row)}</TableRow>)
        })
        return rows;
    }

    function renderCells(row) {
        let cells = [];
        for(const [key, value] of Object.entries(row)) {
            cells.push(<Square key={key} piece={value} position={key}/>);
        }
        return cells;
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