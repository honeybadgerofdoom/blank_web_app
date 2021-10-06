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
    const [backgroundColor, setBackgroundColor] = useState('#fff');

    function handleClick() {
        console.log(props.position);
        setBackgroundColor('#111');
    }

    return (
        <TableCell align="center" className={classes.root} style={{backgroundColor: {backgroundColor}}} onClick={handleClick}>{props.piece}</TableCell>
    )
}