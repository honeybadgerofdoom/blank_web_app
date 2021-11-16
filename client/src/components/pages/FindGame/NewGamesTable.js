import React, {useEffect, useState} from "react";
import { Button, Paper, TableCell, TableRow, ButtonGroup } from "@material-ui/core";
import {TableContent, TableControls} from "./findGameTables";
import {sendRequest} from "../../../utils/restfulAPI";
import useIsMountedRef from "../../../utils/useIsMountedRef";

export default function NewGamesTable(props) {
    const mountedRef = useIsMountedRef();

    const [allGames, setAllGames] = useState([]);

    useEffect(() => {
        sendGamesRequest(props.userID).then(newGames => {
            if (mountedRef.current)
                setAllGames(newGames);
        });
    }, []);

    return (
        <Paper elevation={3}>
            <TableControls title="Create a Match and Invite Others">
                <Button color="primary" variant="outlined" fullWidth onClick={() => props.openInvitesModal()}>
                    New Game
                </Button>
            </TableControls>
            <TableContent headers={["Match ID", "Invitations", "Action"]}>
                <GameRows allGames={allGames} openInvitesModal={props.openInvitesModal} />
            </TableContent>
        </Paper>
    );
}

async function sendGamesRequest(userID) {
    const requestBody = { requestType: "game", userID: userID };
    const response = await(sendRequest(requestBody));
    if (!response) {
        console.log(`Error with ${requestBody.requestType} request`)
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
            <TableCell align="center">
                <ActionButtons gameID={game.gameID} openInvitesModal={props.openInvitesModal} />
            </TableCell>
        </TableRow>
    );
}

function ActionButtons(props) {
    return (
        <ButtonGroup variant="text">
            <Button color="primary" onClick={() => props.openInvitesModal(props.gameID)}>
                Invite
            </Button>
            <Button color="secondary">Delete</Button>
        </ButtonGroup>
    );
}