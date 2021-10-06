import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';

const useStyles = makeStyles((theme) => ({
    root: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function PasswordInputField(props) {
    const classes = useStyles();
    const [showPassword, setShowPassword] = useState(false)
    const [value, setValue] = useState('');

    function handleChange(event) {
        setValue(event.target.value);
    }

    function handleClickShowPassword() {
        setShowPassword(!showPassword);
    }

    function handleMouseDownPassword(event) {
        event.preventDefault();
    }

    return (
        <FormControl className={classes.root} variant="outlined">
            <InputLabel htmlFor={props.title}>{props.name}</InputLabel>
            <OutlinedInput
                id={props.title}
                type={showPassword ? 'text' : 'password'}
                value={value}
                onChange={handleChange}
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