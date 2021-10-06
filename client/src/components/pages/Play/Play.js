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
import Square from "./Square";

const useStyles = makeStyles({
    title: {
        margin: "10px",
    },
    board: {
        width: "80vw",
    },
});

export default function Play(props) {
    const classes = useStyles();
    const pieces = {
        white: {
            king: "\u2654",
            queen: "\u2655",
            rook: "\u2656",
            bishop: "\u2657",
            knight: "\u2658",
            pawn: "\u2659"
        },
        black: {
            king: "\u265A",
            queen: "\u265B",
            rook: "\u265C",
            bishop: "\u265D",
            knight: "\u265E",
            pawn: "\u265F"
        },
        empty: "\u1806"
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
                        <TableRow>
                            <Square piece={pieces.black.rook}/>
                            <Square piece={pieces.black.knight}/>
                            <Square piece={pieces.black.bishop}/>
                            <Square piece={pieces.black.queen}/>
                            <Square piece={pieces.black.king}/>
                            <Square piece={pieces.black.bishop}/>
                            <Square piece={pieces.black.knight}/>
                            <Square piece={pieces.black.rook}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                            <Square piece={pieces.black.pawn}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                            <Square piece={pieces.empty}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                            <Square piece={pieces.white.pawn}/>
                        </TableRow>
                        <TableRow>
                            <Square piece={pieces.white.rook}/>
                            <Square piece={pieces.white.knight}/>
                            <Square piece={pieces.white.bishop}/>
                            <Square piece={pieces.white.queen}/>
                            <Square piece={pieces.white.king}/>
                            <Square piece={pieces.white.bishop}/>
                            <Square piece={pieces.white.knight}/>
                            <Square piece={pieces.white.rook}/>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </Grid>
    </>
}