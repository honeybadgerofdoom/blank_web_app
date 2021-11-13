import React, {useEffect, useState} from "react";
import { Button, makeStyles, Paper, TableCell, TableRow, Container, ButtonGroup } from "@material-ui/core";
import {TableContent, TableControls} from "./findGameTables";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    root: {
        margin: "20px"
    },
    newGameButton: {
        width: "100%",
    },
})

export default function NewGamesTable(props) {

}

async function sendGamesRequest(userID) {
    const response = await(sendRequest({ requestType: "game", userID: userID }));
    if (!response) {
        console.log("Error with game request")
        return [];
    }
    //console.log({ gamesResponse: response })
    return response.games;
}

function GameRows(props) {
    return props.allGames.map((game, index) =>
        <TableRow key={index}>
            <TableCell align="center">{game.gameID}</TableCell>
            <TableCell align="center">0</TableCell>
            <TableCell align="right">
                <ButtonGroup variant="text">
                    <Button color="primary">Invite</Button>
                    <Button color="secondary">Delete</Button>
                </ButtonGroup>
            </TableCell>
        </TableRow>
    );
}