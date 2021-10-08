import React, {useEffect, useState} from "react";
import {Button, makeStyles, Typography} from "@material-ui/core";
import {mockChessboard, positionMap} from "./MockChessboard";
import Square from "./Square";
import CustomColumn from "../../../utils/CustomColumn";
import {sendAPIRequest, sendRequest} from "../../../utils/restfulAPI";

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
    const [boardState, setBoardState] = useState([]);

    async function sendBoardRequest() {
        const boardResponse = await(sendRequest({requestType: "board"}, 'http://localhost:8000'));
        if(boardResponse) {
            setBoardState(boardResponse.boardString.reverse());
        }
    }

    function renderBoard() {
        return (
            boardState.map((piece, index) => {
                return <Square clickedSquare={clickedSquare} setClickedSquare={setClickedSquare}
                               highlightedSquares={highlightedSquares} setHighlightedSquare={setHighlightedSquares}
                               key={index} piece={piece} position={positionMap[index]}/>
            })
        )
    }

    if(boardState.length > 0) {
        return (
            <CustomColumn>
                <div className={classes.root}>
                    {renderBoard()}
                </div>
            </CustomColumn>
        )
    }

    else {
        return <CustomColumn><Button variant="outlined" onClick={sendBoardRequest}>Send Board API Request</Button></CustomColumn>
    }
}