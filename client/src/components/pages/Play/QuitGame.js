import React from "react";
import {sendRequest} from "../../../utils/restfulAPI";
import {Button} from "@mui/material";

export default function QuitGame(props) {
    async function sendQuitGameRequest() {
        const gameResponse = await(sendRequest({requestType: "quitGame", userID: props.currentUserID, gameID: props.chosenGame.gameID}));
        if (gameResponse) {
            props.setChosenGame(null);
        }
        else {
            console.log("failed to update database after quiting game");
        }
    }

    function quit() {
        const confirm = window.confirm("Are you sure you would like to quit game?");
        if (confirm) {
           sendQuitGameRequest().then(() => props.refreshGames());
        }
    }

    if (props.chosenGame) {
        return (
            <Button variant="outlined" color="error"  onClick={() =>quit()}> Quit Game </Button>
        );
    }

    return (props.chosenGame);
}



