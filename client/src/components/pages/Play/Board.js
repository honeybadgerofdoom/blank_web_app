import React, {useState} from "react";
import {makeStyles} from "@material-ui/core";
import {mockChessboard, positionMap} from "./MockChessboard";
import Square from "./Square";
import CustomColumn from "../../../utils/CustomColumn";

const useStyles = makeStyles({
    root: {
        width: "90vw",
        maxWidth: "800px",
        margin: "20px",
        boxShadow: "10px 5px 5px #757575",
    },
});

export default function Board() {
    const classes = useStyles();
    const [clickedSquare, setClickedSquare] = useState("");
    const [highlightedSquares, setHighlightedSquares] = useState([]);

    /*
    API NOTES
    Because the client renders things from the top-left to the bottom-right, we need iterate the board in the
    server-side chess class in that same direction. The response from a /chess/board request should be a
    String[] built by iterating the ChessBoard from 'h1' -> 'a8' and adding the toString() for each square. If
    null, add "".
     */

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
        <CustomColumn>
            <div className={classes.root}>
                {renderBoard()}
            </div>
        </CustomColumn>
    )
}