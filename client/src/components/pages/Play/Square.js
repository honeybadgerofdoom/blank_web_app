import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core";
import {squareColors} from "./squareColors";
import {sendAPIRequest, sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles({
    square: {
        float: "left",
        position: "relative",
        width: "12.5%",
        paddingBottom : "12.5%",
        borderWidth: 0.1,
        borderColor: 'black',
        borderStyle: 'solid',
    },
    content: {
        position: "absolute",
        height: "100%",
        width: "100%",
    },
    table: {
        display: "table",
        height: "100%",
        width: "100%",
    },
    tableCell: {
        display: "table-cell",
        verticalAlign: "middle",
        textAlign: "center",
        height: "100%",
        width: "100%",
        fontSize: "5vw",
    },
});

export default function Square(props) {
    const classes = useStyles();
    const [squareColor, setSquareColor] = useState();

    useEffect(() => {
        if(props.clickedSquare === props.position) {
            setSquareColor(squareColors.clickedSquareColor);
        }
        else if(props.highlightedSquares.includes(props.position)) {
            if(props.piece === "") {
                setSquareColor(squareColors.highlightedSquare);
            }
            else {
                setSquareColor((squareColors.captureSquare));
            }
            // Check if square not null!
        }
        else {
            setSquareColor(getSquareColor());
        }
    })

    function getSquareColor() {
        return squareIsBlack() ? squareColors.blackSquare : squareColors.whiteSquare;
    }

    function squareIsBlack() {
        const letter = props.position.substr(0, 1);
        const number = props.position.substr(1, 1);
        const blackOdds = ['a', 'c', 'e', 'g'];
        const blackEvens = ['b', 'd', 'f', 'h'];
        return blackOdds.includes(letter) && number % 2 || blackEvens.includes(letter) && !(number % 2);
    }

    async function sendLegalMovesRequest(position) {
        const legalMovesResponse = await(sendRequest({requestType: "legalMoves", position: position, userID: props.userID}, 'http://localhost:8000'));
        if(legalMovesResponse) {
            props.setHighlightedSquares(legalMovesResponse.legalMoves);
        }
        else {
            console.log("legalMovesResponse is null");
        }
    }

    async function sendMoveRequest() {
        const moveResponse = await sendRequest({requestType: "move", fromPosition: props.fromPosition, toPosition: props.position, userID: props.userID}, "http://localhost:8000");
        if(moveResponse) {
            console.log("move successful");
            console.log({moveResponse})
            const boardState = props.getBoardState(moveResponse.newBoardState);
            props.setBoardState(boardState);
            props.setClickedSquare("");
            props.setHighlightedSquares([]);
        }
        else{
            console.log("move failed");
        }
    }

    function handleClick() {
        if(props.fromPosition === "" && props.piece !== "") {
            props.setFromPosition(props.position);
            props.setClickedSquare(props.position);
            sendLegalMovesRequest(props.position);
        }
        else if(props.fromPosition !== "" && props.piece !== "" && squareColor !== squareColors.captureSquare) {
            props.setFromPosition(props.position);
            props.setClickedSquare(props.position);
            sendLegalMovesRequest(props.position);
        }
        else if((props.fromPosition !== "" && props.piece === "" && squareColor === squareColors.highlightedSquare) || props.fromPosition !== "" && props.piece !== "" && squareColor === squareColors.captureSquare) {
            console.log("Send the Move API Request with fromPosition: " + props.fromPosition + " toPosition: " + props.position);
            sendMoveRequest();
            props.setFromPosition("");
        }
        else {
            props.setFromPosition("");
            props.setClickedSquare("");
            props.setHighlightedSquares([]);
        }
    }

    return (
        <div className={classes.square}>
            <div className={classes.content} style={{background: `${squareColor}`}} onClick={handleClick}>
                <div className={classes.table}>
                    <div className={classes.tableCell}>
                        {props.piece}
                    </div>
                </div>
            </div>
        </div>
    )
}