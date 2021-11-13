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
    const classes = useStyles();
    const [allGames, setAllGames] = useState([]);

    useEffect(() => {
        sendGamesRequest(props.userID).then(newGames => setAllGames(newGames));
    }, []);

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                <TableControls title="Create a Match and Invite Others">
                    <Button className={classes.newGameButton} color="primary" variant="outlined">
                        New Game
                    </Button>
                </TableControls>
                <TableContent headers={["Match ID", "Invitations", "Action"]}>
                    <GameRows allGames={allGames} />
                </TableContent>
            </Paper>
        </Container>
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
            <TableCell align="right">
                <ButtonGroup variant="text">
                    <Button color="primary">Invite</Button>
                    <Button color="secondary">Delete</Button>
                </ButtonGroup>
            </TableCell>
        </TableRow>
    );
}