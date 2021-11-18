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
import {Typography} from "@mui/material";

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

   
    if(props.chosenGame) {
        return (
            <Button alignItems="flex-start"
                color="black" onClick={() =>quit()}>QuitGame
            </Button>
    );
           
     }
    

    return (
            props.chosenGame
    );
}

function quit(){
    var confirm = window.confirm("Are you sure you would like to quit game?");
if (confirm) {
    //some code
}
else {
    //some code
}
}


