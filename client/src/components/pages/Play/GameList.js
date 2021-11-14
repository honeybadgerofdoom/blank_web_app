import React, {useEffect, useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Box, Typography, Paper, makeStyles, TextField} from "@material-ui/core";
import ListItemButton from '@mui/material/ListItemButton';
import * as PropTypes from "prop-types";
// import ListItemText from '@mui/material/ListItemText';
// import { FixedSizeList } from 'react-window';

const useStyles = makeStyles({
    root: {
        overflow: "auto",
    },
});

function SearchBar(props) {
    return null;
}

SearchBar.propTypes = {
    onCancelSearch: PropTypes.func,
    placeholder: PropTypes.string,
    onChange: PropTypes.func,
    value: PropTypes.any
};
export default function GameList(props) {
    const classes = useStyles();
    const [allGames, setAllGames] = useState([]);

    useEffect(() => {
        sendGameRequest(allGames)
    }, [props.currentUserID]);

    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID}));
        if(gameResponse) {
            setAllGames(gameResponse.games);
        }
        else {
            console.log("game request failed");
        }
    }

    // function renderRow() {
    //     return allGames.map((game, index)=>{
    //         if(game.opponentName !== '') {
    //             return (
    //                 <ListItem key={index}>
    //                     <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={game}/>
    //                 </ListItem>
    //             );
    //         }
    //         else{
    //             return null;
    //         }
    //     })
    // }

    function renderRow() {
        return allGames.map((game, index)=>{
            if(game.opponentName !== "[Pending]") {
                return (
                    <ListItem key={index}>
                        <Button onClick={() => props.setChosenGame(game)}>Play With {game.opponentName}</Button>
                    </ListItem>
                );

            }
            else{
                return null;
            }
        })
    }

    function displayList() {
        if(allGames.length !== 0){
            return (
                <Paper elevation={3} className={classes.root}>
                    <List>
                        {renderRow()}
                    </List>
                </Paper>
            );
        }

        else {
            return null
        }
    }
    return<>
        {displayList()}
    </>
}