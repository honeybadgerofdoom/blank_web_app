import React, {useState} from "react";
import {
    Checkbox,
    FormControlLabel,
} from "@material-ui/core";

export default function ToDoItem(props) {
    const [checked, setChecked] = useState(props.item[1]);

    return (
        <FormControlLabel
            control={<Checkbox checked={checked} onChange={() => {setChecked(!checked)}} />}
            label={props.item[0]}
        />
    )
}
