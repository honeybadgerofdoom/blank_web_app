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
        setFilteredGames(props.allGames);
        setFilter("");
    }, [props.allGames.length]);

    function search(event) {
        const input = event.target.value;
        const matches = props.allGames.filter(game => searchForOpponent(game.opponentName, input.toLowerCase()));
        setFilteredGames(matches);
        setFilter(input);
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
        return (
            <TableRow key={index}>
                <TableCell align="center" className={className}>
                    <Button onClick={() => props.setChosenGame(game)}>Play With {game.opponentName}</Button>
                </TableCell>
            </TableRow>
        )
    });
}

function searchForOpponent(opponentName, input) {
    for (let i = 0; i < input.length; i++) {
        if (opponentName.charAt(i) !== input.charAt(i)) {
            return false;
        }
    }
    return true;
}