import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import {Input} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    root: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function PasswordInputField(props) {
    const classes = useStyles();
    const [showPassword, setShowPassword] = useState(false);

    function handleChange(event) {
        props.setValue(event.target.value);
    }

    function handleClickShowPassword() {
        setShowPassword(!showPassword);
    }

    function handleMouseDownPassword(event) {
        event.preventDefault();
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
                type={showPassword ? 'text' : 'password'}
                value={props.value}
                onChange={handleChange}
                onKeyDown={handleKeyDown}
                endAdornment={
                    <InputAdornment position="end">
                        <IconButton
                            aria-label="toggle password visibility"
                            onClick={handleClickShowPassword}
                            onMouseDown={handleMouseDownPassword}
                            edge="end"
                        >
                            {showPassword ? <Visibility /> : <VisibilityOff />}
                        </IconButton>
                    </InputAdornment>
                }
            />
        </FormControl>
    );
}