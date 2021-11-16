import React, {useEffect, useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Box, Typography, Paper, makeStyles, Grid, Container} from "@material-ui/core";
import ListItemButton from '@mui/material/ListItemButton';
import GameList from "./GameList";
import MyUserInfo from "../Profile/MyUserInfo";
import MyStats from "../Profile/MyStats";
import ViewOtherUsers from "../Profile/ViewOtherUsers";
import {Stack} from "@mui/material";
// import ListItemText from '@mui/material/ListItemText';

// import { FixedSizeList } from 'react-window';


const useStyles = makeStyles({
    root: {
        padding: "10px",
    },

});

export default function Play(props) {
    const classes = useStyles();
    const [chosenGame, setChosenGame] = useState(null);

    return(
        <Stack direction="row" spacing={0} alignItems="flex-start" justifyContent="center" className={classes.root}>
            <GameList setChosenGame={setChosenGame} showMessage={props.showMessage} currentUserID={props.currentUserID}/>
            <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={chosenGame}/>
        </Stack>
    );

}