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
            const theBoard = boardResponse.boardString;
            let allRows = [];
            for(let i = 0; i < 8; i++) {
                let thisRow = [];
                for(let j = 0; j < 8; j++) {
                    thisRow.push(theBoard[(i*8) + (j)])
                }
                allRows.push(thisRow);
            }
            allRows.reverse();
            const masterBoard = allRows[0].concat(allRows[1], allRows[2], allRows[3], allRows[4], allRows[5], allRows[6], allRows[7]);
            setBoardState(masterBoard);
        }
    }

    function renderBoard() {
        return (
            boardState.map((piece, index) => {
                return <Square clickedSquare={clickedSquare} setClickedSquare={setClickedSquare}
                               highlightedSquares={highlightedSquares} setHighlightedSquares={setHighlightedSquares}
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