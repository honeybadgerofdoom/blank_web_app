import React, {useEffect, useState} from "react";
import {makeStyles, TableCell} from "@material-ui/core";
import {squareColors} from "./squareColors";

const useStyles = makeStyles({
    root: {
        borderWidth: 1,
        borderColor: 'black',
        borderStyle: 'solid',
        height: "10vh",
        width: "10vw",
        fontSize: "32pt",
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
        else {
            props.setClickedSquare("");
        }
        const position = props.position;
        console.log({position});
    }

    return (
        <TableCell id={props.position} style={{background: `${squareColor}`}} align="center" className={classes.root} onClick={handleClick}>{props.piece}</TableCell>
    )
}