import React, {useState} from "react";
import Board from "./Board"
import {makeStyles} from "@material-ui/core";
import GameList from "./GameList";
import {Stack} from "@mui/material";
import QuitGame from "./QuitGame";


const useStyles = makeStyles({
    root: {
        padding: "10px",
    },
});

export default function Play(props) {
    const classes = useStyles();
    const [chosenGame, setChosenGame] = useState(null);
    const [highlightedSquares, setHighlightedSquares] = useState([]);
    const [clickedSquare, setClickedSquare] = useState("");

    return(
        <Stack direction="row" spacing={4} alignItems="flex-start" justifyContent="center" className={classes.root}>
            <Stack spacing={4}>
                <GameList setChosenGame={setChosenGame} showMessage={props.showMessage} currentUserID={props.currentUserID} chosenGame={chosenGame}/>
                <QuitGame currentUserID={props.currentUserID} chosenGame={chosenGame} setChosenGame={setChosenGame}/>
            </Stack>
            <Stack spacing={4} justifyContent="center">
            <Board clickedSquare={clickedSquare} setClickedSquare={setClickedSquare}
                   highlightedSquares={highlightedSquares} setHighlightedSquares={setHighlightedSquares}
                   currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={chosenGame}
                   setChosenGame={setChosenGame} flipped={chosenGame && chosenGame.myColor === "BLACK"} />
            </Stack>
        </Stack>
    );
}