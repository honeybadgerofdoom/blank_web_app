import React from "react";
import {
    makeStyles,
    Typography,
    Accordion,
    AccordionSummary, AccordionDetails, Divider
} from "@material-ui/core";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

const useStyles = makeStyles( {
    root: {
        width: "100%"
    },
});

export default function OtherUser(props) {
    const classes = useStyles();

    return (
        <Accordion className={classes.root}>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                {props.user.username}
            </AccordionSummary>
            <Divider/>
            <AccordionDetails>
                <Typography>
                    Bio: {props.user.bio === "" ? "No Bio Yet" : props.user.bio}
                    <br/>
                    Wins: Implement This
                    <br/>
                    Losses: Implement This
                </Typography>
            </AccordionDetails>
        </Accordion>
    )
}