import React, {useState} from "react";
import {Grid, makeStyles} from "@material-ui/core";
import {mockChessboard, positionMap} from "./MockChessboard";
import Square from "./Square";

const useStyles = makeStyles({
    root: {
        width: "70vw",
        margin: "20px",
        boxShadow: "10px 5px 5px #757575",
    },
});

export default function Board() {
    const classes = useStyles();
    const [clickedSquare, setClickedSquare] = useState("");
    const [highlightedSquares, setHighlightedSquares] = useState([]);

    function renderBoard() {
        return (
            mockChessboard.map((piece, index) => {
                return <Square clickedSquare={clickedSquare} setClickedSquare={setClickedSquare}
                               highlightedSquares={highlightedSquares} setHighlightedSquare={setHighlightedSquares}
                               key={index} piece={piece} position={positionMap[index]}/>
            })
        )
    }

    return (
        <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
        >
            <div className={classes.root}>
                {renderBoard()}
            </div>
        </Grid>
    )
}