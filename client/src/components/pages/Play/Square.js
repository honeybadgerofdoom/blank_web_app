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
        const legalMovesResponse = await(sendRequest({requestType: "legalMoves", position: position, userID: props.userID}));
        if(legalMovesResponse) {
            props.setHighlightedSquares(legalMovesResponse.legalMoves);
        }
        else {
            console.log("legalMovesResponse is null");
        }
    }

    async function sendMoveRequest() {
        const moveResponse = await sendRequest({requestType: "move", fromPosition: props.fromPosition, toPosition: props.position, userID: props.userID});
        if(moveResponse.verifyPlayerColor) {
            if (moveResponse.turnValid) {
                const boardState = props.getBoardState(moveResponse.newBoardState);
                props.setBoardState(boardState);
            } else {
                props.showMessage("It's not your turn!", "error");
            }
        }
        else {
            props.showMessage("You can only move your own pieces", "error");
        }
        resetBoardStateVars();
    }

    function handleClick() {
        if(notCallingMove()) {
            resetBoardVisuals();
        }
        else if(aMoveIsPossible()) {
            sendMoveRequest();
        }
        else {
            resetBoardStateVars();
        }
    }

    function resetBoardStateVars() {
        props.setFromPosition("");
        props.setClickedSquare("");
        props.setHighlightedSquares([]);
    }

    function resetBoardVisuals() {
        props.setFromPosition(props.position);
        props.setClickedSquare(props.position);
        sendLegalMovesRequest(props.position);
    }

    function notCallingMove() {
        return (props.fromPosition === "" && props.piece !== "") || (props.fromPosition !== "" && props.piece !== "" && squareColor !== squareColors.captureSquare);
    }

    function aMoveIsPossible() {
        return (props.fromPosition !== "" && props.piece === "" && squareColor === squareColors.highlightedSquare) || (props.fromPosition !== "" && props.piece !== "" && squareColor === squareColors.captureSquare);
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
    );
}