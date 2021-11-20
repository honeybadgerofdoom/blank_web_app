import React, {useEffect, useState} from "react";
import {sendRequest} from "../../../utils/restfulAPI";
import {
    Button,
    Paper,
    makeStyles,
    Container,
    TextField,
    TableRow,
    TableCell,
} from "@material-ui/core";
import {TableContent, TableControls} from "../FindGame/findGameTables";
import {Typography} from "@mui/material"

const useStyles = makeStyles({
    root: {
        overflow: "auto",
    },
    container: {
        maxWidth: "auto"
    },
    search: {
        width: "100%",
    },
});

export default function QuitGame(props) {
    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "quitGame", userID: props.currentUserID, gameID: props.chosenGame.gameID}));
        if (gameResponse) {
            props.setChosenGame = null;
            setAllGames(gameResponse.games);
        }
        else {
            console.log("failed to update database after quiting game");
        }
    }

    function quit(){
        var confirm = window.confirm("Are you sure you would like to quit game?");
        if (confirm) {
           sendGameRequest();
        }
    }

    if(props.chosenGame) {
        return (
            <Button 
                background-color = "black"
                color="white" onClick={() =>quit()}>QuitGame
            </Button>
    );
           
     }
    

    return (
            props.chosenGame
    );
}



