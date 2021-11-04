import React, {useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button} from "@material-ui/core";

export default function Play(props) {
    const [gameID, setGameID] = useState(null);
    const [allGames, setAllGames] = useState([]);

    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID}));
        if(gameResponse) {
            console.log({gameResponse});
        }
        else {
            console.log("game request failed");
        }
    }
    return <Button variant="outlined" size="large" onClick={sendGameRequest}>Send that game</Button>
    //return <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGameID />
}