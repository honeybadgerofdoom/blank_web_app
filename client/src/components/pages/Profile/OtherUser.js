import React from "react";
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

export default function OtherUser(props) {
    const classes = useStyles();

    return (
        <Accordion className={classes.root}>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                {props.user.username}
            </AccordionSummary>
            <AccordionDetails>
                <Typography>
                    Bio: {props.user.bio}
                    <br/>
                    Win/Loss: Implement Me!
                </Typography>
            </AccordionDetails>
        </Accordion>
    )
}