import React, {useEffect, useState} from "react";
import {makeStyles, TableCell} from "@material-ui/core";

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
    // const [legalMoves, setLegalMoves] = useState([]);

    useEffect(() => {
        if(props.clickedSquare === props.position) {
            const clickedSquareColor = "#0011ff";
            setSquareColor(clickedSquareColor);
        }
        else if(props.highlightedSquares.includes(props.position)) {
            const highlightedSquare = "#6b97ff";
            setSquareColor(highlightedSquare);
        }
        else {
            setSquareColor(getSquareColor());
        }
    })

    function getSquareColor() {
        const whiteSquare = "#fff5db";
        const blackSquare = "#a39d8c";
        return squareIsBlack() ? blackSquare : whiteSquare;
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
        }
        const legalMovesResponse = await(sendAPIRequest({requestType: "legalMoves", position: {position}});
        console.log({legalMovesResponse});
        if(legalMovesResponse) {
            props.setHighlightedSquares(legalMovesResponse);
        }
        else {
            console.log("ERROR");
        }
         */
        if(props.piece !== "") {
            props.setClickedSquare(props.position);
        }
        const position = props.position;
        const piece = props.piece;
        console.log({position});
        console.log({piece});
    }

    return (
        <TableCell id={props.position} style={{background: `${squareColor}`}} align="center" className={classes.root} onClick={handleClick}>{props.piece}</TableCell>
    )
}