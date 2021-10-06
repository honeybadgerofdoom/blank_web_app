import React, {useState} from "react";
import {makeStyles, TableCell} from "@material-ui/core";

const useStyles = makeStyles({
    root: {
        borderWidth: 1,
        borderColor: 'black',
        borderStyle: 'solid',
        height: "10vh",
        width: "10vw",
        fontSize: "32pt",
    },
});

export default function Play(props) {
    const classes = useStyles();

    function handleClick() {
        console.log(props.position);
    }

    return (
        <TableCell align="center" className={classes.root} onClick={handleClick}>{props.piece}</TableCell>
    )
}