import React, {useState} from "react";
import {Button, makeStyles} from "@material-ui/core";
import Square from "./Square";
import CustomColumn from "../../../utils/CustomColumn";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles({
    root: {
        width: "90vw",
        maxWidth: "800px",
        margin: "20px",
        boxShadow: "10px 5px 5px #757575",
    },
});

export default function Board(props) {
    const classes = useStyles();
    const [clickedSquare, setClickedSquare] = useState("");
    const [highlightedSquares, setHighlightedSquares] = useState([]);
    const [fromPosition, setFromPosition] = useState("");
    const [boardState, setBoardState] = useState([]);

    async function sendBoardRequest() {
        const boardResponse = await(sendRequest({requestType: "board", userID: props.currentUserID, gameID: props.chosenGame.gameID}));
        if(boardResponse) {
            setBoardState(getBoardState(boardResponse.boardString));
        }
        else {
            console.log("board request failed")
        }
    }

    function getBoardState(theBoard) {
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
        return masterBoard;
    }

    function renderBoard() {
        return (
            boardState.map((piece, index) => {
                return <Square clickedSquare={clickedSquare} setClickedSquare={setClickedSquare} setBoardState={setBoardState}
                               highlightedSquares={highlightedSquares} setHighlightedSquares={setHighlightedSquares}
                               key={index} piece={piece} position={generateMappingArray(index)} userID={props.currentUserID}
                               fromPosition={fromPosition} setFromPosition={setFromPosition} getBoardState={getBoardState} showMessage={props.showMessage}/>
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
        if(props.chosenGame.opponentName !== '') {
            return <CustomColumn><Button variant="outlined" onClick={sendBoardRequest}>Play Game
                With {props.chosenGame.opponentName}</Button></CustomColumn>
        }
        else{
            return null;
        }
    }
}

function generateMappingArray(index) {
    let stringArray = [];
    const letterArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
    const numberArray = ['8', '7', '6', '5', '4', '3', '2', '1'];
    numberArray.forEach((number) => {
        for(let i = 0; i < 8; i++) {
            stringArray.push(letterArray[i] + number);
        }
    })
    return stringArray[index];
}