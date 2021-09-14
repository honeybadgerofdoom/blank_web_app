import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import {Grid, Paper, Typography} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    shortTextField: {
        width: '25ch',
        margin: theme.spacing(1),
    },
    paper: {
        width: "60vw",
        margin: theme.spacing(1),
        padding: theme.spacing(2),
    },
}));

export default function InputAdornments() {
    const classes = useStyles();
    const [values, setValues] = React.useState({
        firstName: '',
        lastName: '',
        userName: '',
        email: '',
        password: '',
        confirmPassword: '',
        showPassword: false,
    });

    const handleChange = (prop) => (event) => {
        setValues({ ...values, [prop]: event.target.value });
    };

    const handleClickShowPassword = () => {
        setValues({ ...values, showPassword: !values.showPassword });
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    return (
        <Grid
            className={classes.root}
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
        >
            <Paper elevation={3} className={classes.paper}>
                <Grid item>
                    <Typography align="center">Registration</Typography>
                </Grid>
                <Grid item>
                    <FormControl className={classes.shortTextField} variant="outlined">
                        <InputLabel htmlFor="first-name">First Name</InputLabel>
                        <OutlinedInput
                            id="first-name"
                            value={values.firstName}
                            onChange={handleChange('firstName')}
                        />
                    </FormControl>
                    <FormControl className={classes.shortTextField} variant="outlined">
                        <InputLabel htmlFor="last-name">Last Name</InputLabel>
                        <OutlinedInput
                            id="last-name"
                            value={values.lastName}
                            onChange={handleChange('lastName')}
                        />
                    </FormControl>
                    <FormControl className={classes.shortTextField} variant="outlined">
                        <InputLabel htmlFor="user-name">Username</InputLabel>
                        <OutlinedInput
                            id="user-name"
                            value={values.userName}
                            onChange={handleChange('userName')}
                        />
                    </FormControl>

                </Grid>
                <Grid item>
                    <FormControl className={classes.shortTextField} variant="outlined">
                        <InputLabel htmlFor="email">Email Address</InputLabel>
                        <OutlinedInput
                            id="email"
                            value={values.email}
                            onChange={handleChange('email')}
                        />
                    </FormControl>
                    <FormControl className={classes.shortTextField} variant="outlined">
                        <InputLabel htmlFor="password">Password</InputLabel>
                        <OutlinedInput
                            id="password"
                            type={values.showPassword ? 'text' : 'password'}
                            value={values.password}
                            onChange={handleChange('password')}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                        edge="end"
                                    >
                                        {values.showPassword ? <Visibility /> : <VisibilityOff />}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>
                    <FormControl className={classes.shortTextField} variant="outlined">
                        <InputLabel htmlFor="confirmPassword">Confirm Password</InputLabel>
                        <OutlinedInput
                            id="confirmPassword"
                            type={values.showPassword ? 'text' : 'password'}
                            value={values.password}
                            onChange={handleChange('confirmPassword')}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                        edge="end"
                                    >
                                        {values.showPassword ? <Visibility /> : <VisibilityOff />}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>
                </Grid>
            </Paper>
        </Grid>
    );
}