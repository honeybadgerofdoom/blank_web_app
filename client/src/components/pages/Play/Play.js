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

    return(
        <><QuitGame currentUserID={props.currentUserID} chosenGame={chosenGame} setChosenGame={setChosenGame} />
        <Stack direction="row" spacing={2} alignItems="flex-start" justifyContent="center" className={classes.root}>
            <GameList setChosenGame={setChosenGame} showMessage={props.showMessage} currentUserID={props.currentUserID}/>
            <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={chosenGame} setChosenGame={setChosenGame} />
        </Stack>
        </>
        
    );

}