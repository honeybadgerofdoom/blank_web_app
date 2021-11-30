import React, {useEffect, useState} from "react";
import {sendRequest} from "../../../utils/restfulAPI";
import {makeStyles} from "@material-ui/core";
import {Button} from "@mui/material";

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
        }
        else {
            console.log("failed to update database after quiting game");
        }
    }

    function quit(){
        var confirm = window.confirm("Are you sure you would like to quit game?");
        if (confirm) {
           sendGameRequest();
           refresh();
        }
    }

    function refresh(){
        window.location.reload(true);
    }

    if(props.chosenGame) {
        return (
            <Button variant="outlined" color="error"  onClick={() =>quit()}> Quit Game </Button>
    );     
     }
    

    return (props.chosenGame);
}



