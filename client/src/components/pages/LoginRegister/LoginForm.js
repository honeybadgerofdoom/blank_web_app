import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {Button, Grid, Paper, Typography} from "@material-ui/core";
import CustomInputField from "./CustomInputField";
import PasswordInputField from "./PasswordInputField";

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
                        <Grid
                            className={classes.root}
                            container
                            direction="column"
                            justifyContent="center"
                            alignItems="center"
                        >
                            <Grid item>
                                <Typography variant="h4" align="center">Log In</Typography>
                                <br/>
                            </Grid>
                            <Grid item>
                                <CustomInputField title="username" name="Username"/>
                                <PasswordInputField title="password" name="Password"/>
                            </Grid>
                            <Grid item>
                                <Button className={classes.shortTextField} variant="outlined" size="large">Log In</Button>
                            </Grid>
                        </Grid>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}