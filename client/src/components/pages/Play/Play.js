import React, {useEffect, useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Box, Typography, Paper, makeStyles, Grid} from "@material-ui/core";
import ListItemButton from '@mui/material/ListItemButton';
import GameList from "./GameList";
import MyUserInfo from "../Profile/MyUserInfo";
import MyStats from "../Profile/MyStats";
import ViewOtherUsers from "../Profile/ViewOtherUsers";
// import ListItemText from '@mui/material/ListItemText';

// import { FixedSizeList } from 'react-window';


const useStyles = makeStyles({
    root: {
        overflow: "auto",
    },
});

export default function Play(props) {
    return<>

        <Grid container direction="row" justifyContent="center" alignItems="flex-start">
            <Grid item>
                <Grid container direction="column" justifyContent="center" alignItems="flex-start">
                    <GameList showMessage={props.showMessage} currentUserID={props.currentUserID} />
                </Grid>
            </Grid>
            <Grid item>
                {/*<ViewOtherUsers showMessage={props.showMessage} currentUserID={props.currentUserID} />*/}
            </Grid>
        </Grid>
        </>
}