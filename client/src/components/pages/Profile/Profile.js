import React, {useEffect, useState} from "react";
import {Button, Container, Grid, makeStyles, Paper, TextField, Typography} from "@material-ui/core";
import {sendRequest} from "../../../utils/restfulAPI";

const useStyles = makeStyles( {
    root: {
        margin: "20px",
        padding: "20px",
    },
    gridItem: {
        margin: "10px",
        width: "100%"
    },
    textField: {
        width: "100%",
    }
});

export default function Profile(props) {
    const classes = useStyles();
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [bio, setBio] = useState("");
    const [picURL, setPicURL] = useState("");

    console.log({bio})

    useEffect(() => {
        myProfileRequest();
    })

    async function myProfileRequest() {
        const response = await sendRequest({requestType: "myProfile", userID: props.currentUserID});
        if(response) {
            setNickname(response.nickname);
            setEmail(response.email);
            setBio(response.bio);
            setPicURL(response.picURL);
        }
    }

    function getBioPlaceholder() {
        return bio === undefined ? "Add a Bio..." : bio;
    }

    function getNicknamePlaceholder() {
        return `nickname: ${nickname}`
    }

    function getEmailPlaceholder() {
        return `email: ${email}`
    }

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                <Grid container direction="column" justifyContent="flex-start" alignItems="center">
                    <Grid item className={classes.gridItem}>
                        <TextField
                            className={classes.textField}
                            variant="outlined"
                            placeholder={getNicknamePlaceholder()}
                        />
                    </Grid>
                    <Grid item className={classes.gridItem}>
                        <TextField
                            className={classes.textField}
                            variant="outlined"
                            placeholder={getEmailPlaceholder()}
                        />
                    </Grid>
                    <Grid item className={classes.gridItem}>
                        <TextField
                            className={classes.textField}
                            variant="outlined"
                            placeholder={getBioPlaceholder()}
                            multiline
                            maxRows={4}
                        />
                    </Grid>
                    <Grid item>
                        <Button variant="outlined">
                            Update My Info
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Container>
    )
}