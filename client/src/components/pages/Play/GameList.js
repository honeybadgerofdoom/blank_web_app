import React, {useEffect, useState} from "react";
import {Button, Paper, makeStyles, TextField, TableRow, TableCell} from "@material-ui/core";
import {TableContent, TableControls} from "../FindGame/findGameTables";
import {Typography} from "@mui/material";

const useStyles = makeStyles({
    root: {
        overflow: "auto",
    },
    container: {
        maxWidth: "auto"
    },
    search: {
        width: "100%",
    },
    rowColor: {
        background: "#4caf50",
    }
});

export default function GameList(props) {
    const classes = useStyles();
    const [filter, setFilter] = useState("");
    const [filteredGames, setFilteredGames] = useState([]);

    useEffect(() => {
        setFilteredGames(getFilteredGames(filter));
    }, [props.allGames.length]);

    function search(event) {
        const input = event.target.value.toLowerCase();
        const matches = getFilteredGames(input);
        setFilteredGames(matches);
        setFilter(input);
    }

    function getFilteredGames(filterText) {
        return props.allGames.filter(game => searchForOpponent(game.opponentName, filterText));
    }

    if (props.allGames.length === 0) {
        return (
            <Typography>
                You are not involved in any matches 😭! To find matches, go to the Find Game page!
            </Typography>
        );
    }

    return (
        <Paper elevation={3} className={classes.root}>
            <TableControls title="Choose a Game To Play">
                <TextField className={classes.search} size="small" variant="outlined" onChange={search} value={filter} placeholder="Search Games..." />
            </TableControls>
            <TableContent headers={[]}>
                <DisplayGameList {...props} games={filteredGames}/>
            </TableContent>
        </Paper>
    );
}

function DisplayGameList(props) {
    const classes = useStyles();

    function getBackgroundColor(selectedGame) {
        if (selectedGame === props.chosenGame) {
            return classes.rowColor;
        } else {
            return "";
        }
    }

    return props.games.map((game, index) => {
        const className = getBackgroundColor(game);
        const buttonLabel = `Play With ${game.opponentName}`;
        return (
            <TableRow key={index}>
                <TableCell align="center" className={className}>
                    <Button onClick={() => props.setChosenGame(game)}>
                        {props.myTurnList[index] ? turnifyLabel(buttonLabel) : buttonLabel}
                    </Button>
                </TableCell>
            </TableRow>
        )
    });
}

function turnifyLabel(label) {
    return "♟️ " + label + " ♟️";
}

function searchForOpponent(opponentName, input) {
    for (let i = 0; i < input.length; i++) {
        if (opponentName.charAt(i) !== input.charAt(i)) {
            return false;
        }
    }
    return true;
}