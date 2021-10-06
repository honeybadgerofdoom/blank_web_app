import React from 'react';
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

export default function RegistrationForm() {
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
                        <CustomColumn>
                            <Grid item>
                                <Typography variant="h4" align="center">Register</Typography>
                                <br/>
                            </Grid>
                            <Grid item>
                                <CustomInputField title="first-name" name="First Name"/>
                                <CustomInputField title="last-name" name="Last Name"/>
                                <CustomInputField title="username" name="Username"/>
                            </Grid>
                            <Grid item>
                                <CustomInputField title="email" name="Email"/>
                                <PasswordInputField title="password" name="Password"/>
                                <PasswordInputField title="comfirm-password" name="Confirm Password"/>
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