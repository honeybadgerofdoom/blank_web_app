import React, {useEffect, useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Box, Typography} from "@material-ui/core";
import ListItemButton from '@mui/material/ListItemButton';
// import ListItemText from '@mui/material/ListItemText';
// import { FixedSizeList } from 'react-window';

export default function Play(props) {
    const [allGames, setAllGames] = useState([]);
    console.log({allGames})

    useEffect(() => {
        {sendGameRequest(allGames)}
    }, [props.userID]);

    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID}));
        if(gameResponse) {
            setAllGames(gameResponse.games);
        }
        else {
            console.log("game request failed");
        }
    }

    function renderRow() {
        return allGames.map((game, index)=>{
            console.log({game})
            if(game.opponentName !== '') {
                return (
                    <ListItem key={index}>
                        <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={game}/>
                    </ListItem>
                );
            }
            else{
                return null;
            }
        })
    }

    function VirtualizedList() {
        if(allGames.length !== 0){
        return (
            <Box>
                <List>
                    {renderRow()}
                </List>
            </Box>
        );
        }

        else {
            return null
        }
    }

    return<>
        {VirtualizedList()}
        {/*<Button variant="outlined" onClick={sendGameRequest}>Play Game With </Button>*/}
        </>
}