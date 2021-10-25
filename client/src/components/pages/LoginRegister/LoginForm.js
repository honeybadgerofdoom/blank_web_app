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
    formRow: {
        width: "100%",
    },
    shortTextField: {
        minWidth: '18vw',
        margin: theme.spacing(1),
    },
}));

export default function LoginForm() {
    const classes = useStyles();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

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
                                <Button className={classes.shortTextField} variant="outlined" size="large">Log In</Button>
                            </Grid>
                        </CustomColumn>
                    </Paper>
                </Grid>
            </CustomColumn>
        </div>
    );
}