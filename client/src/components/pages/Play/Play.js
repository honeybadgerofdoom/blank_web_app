import React, {useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button} from "@material-ui/core";

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
    return<>
        <Button variant="outlined" size="large" onClick={sendGameRequest}>Send that game</Button>
        <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={allGames[1]} />
        </>
}