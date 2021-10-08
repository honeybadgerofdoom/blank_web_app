import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core";
import {squareColors} from "./squareColors";

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
            setSquareColor(squareColors.highlightedSquare);
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

    function handleClick() {
        /*
        This is essentially the code we want to use on the client to make the API request.
        The request is an object with a type of legal moves and a String position.
        The response should be a String array containing the legal moves, as in A2.

        if(props.piece !== "") {
            props.setClickedSquare(props.position);
            const legalMovesResponse = await(sendAPIRequest({requestType: "legalMoves", position: {position}});
            console.log({legalMovesResponse});
            if(legalMovesResponse) {
                props.setHighlightedSquares(legalMovesResponse);
            }
            else {
                console.log("ERROR");
            }
        }
         */
        if(props.piece !== "") {
            props.setClickedSquare(props.position);
        }
        const position = props.position;
        console.log({position});
    }

    return (
        <div className={classes.square}>
            <div className={classes.content} style={{background: `${squareColor}`}}>
                <div className={classes.table}>
                    <div className={classes.tableCell} onClick={handleClick}>
                        {props.piece}
                    </div>
                </div>
            </div>
        </div>
    )
}