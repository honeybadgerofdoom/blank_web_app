import React, {useState} from "react";
import {makeStyles, Paper} from "@material-ui/core";
import {mockChessboard, positionMap} from "./MockChessboard";
import Square from "./Square";

const useStyles = makeStyles({
    root: {
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
                const position = positionMap[index];
                return <Square clickedSquare={clickedSquare} setClickedSquare={setClickedSquare}
                               highlightedSquares={highlightedSquares} setHighlightedSquare={setHighlightedSquares}
                               key={index} piece={piece} position={position}/>
            })
        )
    }

    return (
        <Paper className={classes.root}>
            {renderBoard()}
        </Paper>
    )
}