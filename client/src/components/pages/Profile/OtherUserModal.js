import React, {useState} from "react";
import {
    makeStyles,
    Typography,
    Accordion,
    AccordionSummary, AccordionDetails
} from "@material-ui/core";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

const useStyles = makeStyles( {
    root: {
        width: "100%"
    },
});

export default function ViewOtherUsers(props) {
    const classes = useStyles();

    return (
        <>
            <Accordion className={classes.root}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                    {props.user.username}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>Bio: {props.user.bio}</Typography>
                    <Typography>Win/Loss: Implement Me!</Typography>
                </AccordionDetails>
            </Accordion>
        </>
    )
}