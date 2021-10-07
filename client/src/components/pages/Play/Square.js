import React from "react";
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
    // const [legalMoves, setLegalMoves] = useState([]);

    function handleClick() {
        /*
        This is essentially the code we want to use on the client to make the API request.
        The request is an object with a type of legal moves and a String position.
        The response should be a String array containing the legal moves, as in A2.
        
        const legalMovesResponse = await(sendAPIRequest({requestType: "legalMoves", position: {position}});
        console.log({legalMovesResponse});
        if(legalMovesResponse) {
            setLegalMoves(legalMovesResponse);
        }
        else {
            console.log("ERROR");
        }
         */
        console.log(props.position);
    }

    return (
        <TableCell align="center" className={classes.root} onClick={handleClick}>{props.piece}</TableCell>
    )
}