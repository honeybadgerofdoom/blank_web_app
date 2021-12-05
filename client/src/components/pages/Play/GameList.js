import React, {useEffect, useState} from "react";
import {sendRequest} from "../../../utils/restfulAPI";
import {
    Button,
    Paper,
    makeStyles,
    Container,
    TextField,
    TableRow,
    TableCell,
} from "@material-ui/core";
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
    const [allGames, setAllGames] = useState([]);
    const [filteredGames, setFilteredGames] = useState([]);
    const [filtering, setFiltering] = useState(false);

    const games = filtering ? filteredGames : allGames;

    useEffect(() => {
        sendGameRequest(allGames)
    }, [props.currentUserID]);

    function search(event) {
        const input = event.target.value;
        setFiltering(input !== "");
        const matches = allGames.filter(game => searchForOpponent(game.opponentName, input.toLowerCase()));
        setFilteredGames(matches);
    }

    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID, type: "ACTIVE"}));
        if (gameResponse) {
            setAllGames(gameResponse.games);
        }
        else {
            console.log("game request failed");
        }
    }

    if (allGames.length === 0) {
        return(
            <Typography>
                You are not involved in any matches 😭! To find matches, go to the Find Game page!
            </Typography>
        )
    }

    function getBackgroundColor(selectedGame){
        if(selectedGame === props.chosenGame){
            return classes.rowColor;
        }
        else{
            return "";
        }
    }


    function DisplayGameList(props) {
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

    return (
        <Paper elevation={3} className={classes.root}>
            <TableControls title="Choose a Game To Play">
                <TextField className={classes.search} size="small" variant="outlined" onChange={search} placeholder="Search Games..." />
            </TableControls>
            <TableContent headers={[]}>
                <DisplayGameList {...props} games={games}/>
            </TableContent>
        </Paper>
    );
}

function searchForOpponent(opponentName, input) {
    for (let i = 0; i < input.length; i++) {
        if (opponentName.charAt(i) !== input.charAt(i)) {
            return false;
        }
    }
    return true;
}

