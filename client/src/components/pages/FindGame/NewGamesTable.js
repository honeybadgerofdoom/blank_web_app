import React from "react";
import { Button, Paper, TableCell, TableRow, ButtonGroup } from "@material-ui/core";
import {TableContent, TableControls} from "./findGameTables";

export default function NewGamesTable(props) {
    return (
        <Paper elevation={3}>
            <TableControls title="Create a Match and Invite Others">
                <Button color="primary" variant="outlined" fullWidth onClick={() => props.openInvitesModal()}>
                    New Game
                </Button>
            </TableControls>
            <TableContent headers={["Match ID", "Invitations", "Action"]}>
                <GameRows allGames={props.allGames} openInvitesModal={props.openInvitesModal} />
            </TableContent>
        </Paper>
    );
}

function GameRows(props) {
    return props.allGames.map((game, index) =>
        <TableRow key={index}>
            <TableCell align="center">{game.gameID}</TableCell>
            <TableCell align="center">{game.totalInvites}</TableCell>
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