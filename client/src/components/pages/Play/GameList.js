import React, {useEffect, useState} from "react";
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Paper, makeStyles} from "@material-ui/core";
import * as PropTypes from "prop-types";

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
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID, type: "ACTIVE"}));
        if (gameResponse) {
            setAllGames(gameResponse.games);
        }
        else {
            console.log("game request failed");
        }
    }

    function renderRow() {
        return allGames.map((game, index) =>
            <ListItem key={index}>
                <Button onClick={() => props.setChosenGame(game)}>Play With {game.opponentName}</Button>
            </ListItem>
        );
    }

    if (allGames.length === 0) {
        return null;
    }

    return (
        <Paper elevation={3} className={classes.root}>
            <List>
                {renderRow()}
            </List>
        </Paper>
    );
}