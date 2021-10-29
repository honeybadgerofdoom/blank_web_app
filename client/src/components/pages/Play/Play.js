import React from "react";
import Board from "./Board"

export default function Play(props) {
    return <Board currentUserID={props.currentUserID} showMessage={props.showMessage} />
}