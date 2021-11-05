import React, {useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Box, Typography} from "@material-ui/core";
import ListItemButton from '@mui/material/ListItemButton';
// import ListItemText from '@mui/material/ListItemText';
// import { FixedSizeList } from 'react-window';

export default function Play(props) {
    const [gameID, setGameID] = useState(null);
    const [allGames, setAllGames] = useState([]);
    console.log({allGames})

    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID}));
        if(gameResponse) {
            setAllGames(gameResponse.gameIDs);
        }
        else {
            console.log("game request failed");
        }
    }

    function renderRow() {
        return allGames.map((gameId, index)=>{
            console.log({gameId})
            return (
                <ListItem key={index}>
                    <Button>
                        {gameId}
                    </Button>
                </ListItem>
            );
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
        <Button variant="outlined" size="large" onClick={sendGameRequest}>Send that game</Button>
        <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={allGames[1]} />
        </>
}