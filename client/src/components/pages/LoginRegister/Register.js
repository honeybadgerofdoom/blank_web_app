import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {Button, Grid, Paper, Typography} from "@material-ui/core";
import CustomInputField from "./CustomInputField";
import PasswordInputField from "./PasswordInputField";
import CustomColumn from "../../../utils/CustomColumn";

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    paper: {
        width: "60vw",
        margin: theme.spacing(1),
        padding: theme.spacing(2),
    },
    shortTextField: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function Register() {
    const classes = useStyles();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    return (
        <div>
            <Grid
                container
                direction="column"
                justifyContent="center"
                alignItems="center"
            >
                <Grid item>
                    <Paper elevation={3} className={classes.paper}>
                        <CustomColumn>
                            <Grid item>
                                <Typography variant="h4" align="center">Register</Typography>
                                <br/>
                            </Grid>
                            <Grid item>
                                <CustomInputField title="first-name" name="First Name" value={firstName} setValue={setFirstName} />
                                <CustomInputField title="last-name" name="Last Name" value={lastName} setValue={setLastName} />
                                <CustomInputField title="username" name="Username" value={username} setValue={setUsername} />
                            </Grid>
                            <Grid item>
                                <CustomInputField title="email" name="Email" value={email} setValue={setEmail} />
                                <PasswordInputField title="password" name="Password" value={password} setValue={setPassword} />
                                <PasswordInputField title="confirm-password" name="Confirm Password" value={confirmPassword} setValue={setConfirmPassword} />
                            </Grid>
                            <Grid item>
                                <Button className={classes.shortTextField} variant="outlined" size="large">Sign Me Up!</Button>
                            </Grid>
                        </CustomColumn>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}