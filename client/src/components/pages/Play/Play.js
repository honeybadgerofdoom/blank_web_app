import React, {useEffect, useState} from "react";
import Board from "./Board"
import {makeStyles} from "@material-ui/core";
import GameList from "./GameList";
import {Stack} from "@mui/material";
import QuitGame from "./QuitGame";
import {sendRequest} from "../../../utils/restfulAPI";


const useStyles = makeStyles({
    root: {
        padding: "10px",
    },

});

export default function Play(props) {
    const classes = useStyles();
    const [allGames, setAllGames] = useState([]);
    const [chosenGame, setChosenGame] = useState(null);
    const [highlightedSquares, setHighlightedSquares] = useState([]);
    const [clickedSquare, setClickedSquare] = useState("");

    function refreshGames() {
        sendGameRequest(props.currentUserID).then(newGames => setAllGames(newGames));
    }

    useEffect(refreshGames, [props.currentUserID]);

    return(
        <Stack direction="row" spacing={2} alignItems="flex-start" justifyContent="center" className={classes.root}>
            <Stack spacing={2} justifyContent="center">
                <GameList setChosenGame={setChosenGame} showMessage={props.showMessage} chosenGame={chosenGame} allGames={allGames} />
                <QuitGame currentUserID={props.currentUserID} chosenGame={chosenGame} setChosenGame={setChosenGame} refreshGames={refreshGames}/>
            </Stack>
            <Board clickedSquare={clickedSquare} setClickedSquare={setClickedSquare} highlightedSquares={highlightedSquares} setHighlightedSquares={setHighlightedSquares} currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={chosenGame} setChosenGame={setChosenGame} />
        </Stack>
        
    );
}

async function sendGameRequest(userID) {
    const gameResponse = await(sendRequest({requestType: "game", userID: userID, type: "ACTIVE"}));
    if (gameResponse) {
        return gameResponse.games;
    }
    else {
        console.log("game request failed");
    }
}