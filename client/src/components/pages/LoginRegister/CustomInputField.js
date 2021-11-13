import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import {Input} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    root: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function CustomInputField(props) {
    const classes = useStyles();

    function handleChange(event) {
        props.setValue(event.target.value);
    }

    function handleKeyDown(event) {
        if (event.keyCode === 13) {
            props.submit();
        }
    }

    return (
        <FormControl className={classes.root}>
            <InputLabel htmlFor={props.title}>{props.name}</InputLabel>
            <Input
                id={props.title}
                value={props.value}
                onChange={handleChange}
                onKeyDown={handleKeyDown}
            />
        </FormControl>
    );
}