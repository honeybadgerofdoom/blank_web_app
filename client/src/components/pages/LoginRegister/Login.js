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
    formRow: {
        width: "100%",
    },
    shortTextField: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function Login(props) {
    const classes = useStyles();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    async function sendLoginRequest() {
        const response = await sendAPIRequest({requestType:"login", username:username, password:password}, "http://localhost:8000");
        if(response) {
            props.setUserAuthenticated(true);
            props.setCurrentUserID(response.userID);
            props.showMessage("Welcome " + response.username); //This throws errors in the console...
        }
        else {
            props.showMessage("Username or Password invalid");
        }
    }

    return (
        <div>
            <CustomColumn>
                <Grid item>
                    <Paper elevation={3} className={classes.paper}>
                        <CustomColumn className={classes.root}>
                            <Grid item>
                                <Typography variant="h4" align="center">Log In</Typography>
                                <br/>
                            </Grid>
                            <Grid item>
                                <CustomInputField title="username" name="Username" value={username} setValue={setUsername} />
                                <PasswordInputField title="password" name="Password" value={password} setValue={setPassword} />
                            </Grid>
                            <Grid item>
                                <Button className={classes.shortTextField} variant="outlined" size="large" onClick={sendLoginRequest}>Log In</Button>
                            </Grid>
                        </CustomColumn>
                    </Paper>
                </Grid>
            </CustomColumn>
        </div>
    );
}