import React from "react";
import {FormControl, FormGroup, FormLabel, makeStyles} from "@material-ui/core";
import ToDoItem from "./ToDoItem";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: theme.spacing(3),
    },
}));

export default function ToDoList(props) {
    const classes = useStyles();

    function getItems() {
        let allItems = [];
        props.list.forEach((item, index) => {
            allItems.push(
                <div key={index}>
                    <ToDoItem item={item}/>
                </div>
            )
        })
        return allItems;
    }

    return (
        <FormControl component="fieldset" className={classes.root}>
            <FormGroup>
                <FormLabel component="legend">Checklist of things we need to do to finish setting up</FormLabel>
                {getItems()}
            </FormGroup>
        </FormControl>
    )
}