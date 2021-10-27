import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {Button, Grid, Paper, Typography} from "@material-ui/core";
import CustomInputField from "./CustomInputField";
import PasswordInputField from "./PasswordInputField";
import CustomColumn from "../../../utils/CustomColumn";
import {sendAPIRequest} from "../../../utils/restfulAPI";

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

export default function Register(props) {
    const classes = useStyles();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    async function sendRegisterRequest() {
        if(password === confirmPassword) {
            const response = await sendAPIRequest({requestType: "register", username: username, password: password, email: email}, "http://localhost:8000");
            if (response) {
                props.showMessage("You have successfully register and are now able to login");
            } else {
                props.showMessage("That username is already taken");
            }
        }
        else {
            props.showMessage("Passwords do not match");
        }
    }

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
                                <CustomInputField title="username" name="Username" value={username} setValue={setUsername} />
                                <CustomInputField title="email" name="Email" value={email} setValue={setEmail} />
                            </Grid>
                            <Grid item>
                                <PasswordInputField title="password" name="Password" value={password} setValue={setPassword} />
                                <PasswordInputField title="comfirm-password" name="Confirm Password" value={confirmPassword} setValue={setConfirmPassword} />
                            </Grid>
                            <Grid item>
                                <Button className={classes.shortTextField} variant="outlined" size="large" onClick={sendRegisterRequest}>Sign Me Up!</Button>
                            </Grid>
                        </CustomColumn>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}