import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';

const useStyles = makeStyles((theme) => ({
    root: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function CustomInputField(props) {
    const classes = useStyles();
    const [value, setValue] = useState('');

    function handleChange(event) {
        setValue(event.target.value);
    };

    return (
        <FormControl className={classes.root} variant="outlined">
            <InputLabel htmlFor={props.title}>{props.name}</InputLabel>
            <OutlinedInput
                id={props.title}
                value={value}
                onChange={handleChange}
            />
        </FormControl>
    );
}