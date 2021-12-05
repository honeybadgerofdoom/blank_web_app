import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core";
import Square from "./Square";
import {sendRequest} from "../../../utils/restfulAPI";
import {Container} from "reactstrap";

const useStyles = makeStyles({
    root: {
        width: "90vw",
        maxWidth: "800px",
        boxShadow: "10px 5px 5px #757575",
    },
});

const POLL_TIME_SECONDS = 2

export default function Board(props) {
    const classes = useStyles();
    const [fromPosition, setFromPosition] = useState("");
    const [boardState, setBoardState] = useState([]);

    useEffect(() => {
        if(props.chosenGame) {
            sendBoardRequest();
            props.setHighlightedSquares([]);
            props.setClickedSquare("");
        }
    }, [props.chosenGame]);

    useEffect(() => {
        const interval = setInterval(() => {
            if (props.chosenGame) {
                sendBoardRequest();
                props.refreshGames();
            }
        }, POLL_TIME_SECONDS * 1000);
        return () => clearInterval(interval);
    }, [props.chosenGame]);

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
                return <Square clickedSquare={props.clickedSquare} setClickedSquare={props.setClickedSquare} setBoardState={setBoardState}
                               highlightedSquares={props.highlightedSquares} setHighlightedSquares={props.setHighlightedSquares}
                               key={index} piece={piece} position={generateMappingArray(index)} userID={props.currentUserID}
                               fromPosition={fromPosition} setFromPosition={setFromPosition} getBoardState={getBoardState}
                               showMessage={props.showMessage} gameID={props.chosenGame.gameID}
                               setChosenGame={props.setChosenGame} opponent={props.chosenGame.opponentName} />
            })
        )
    }

    if(boardState.length > 0 && props.chosenGame) {
        return (
            <Container>
                <div className={classes.root}>
                    {renderBoard()}
                </div>
            </Container>
        )
    }
    return null;
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