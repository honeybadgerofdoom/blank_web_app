import React, {useEffect, useState} from "react";
import {Button, Container, Grid, makeStyles, Paper, TextField} from "@material-ui/core";
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

    const [newNickname, setNewNickname] = useState("");
    const [newEmail, setNewEmail] = useState("");
    const [newBio, setNewBio] = useState("");
    const [newPicURL, setNewPicURL] = useState("");

    useEffect(() => {
        sendMyProfileRequest();
    })

    async function sendMyProfileRequest() {
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

    function updateNickname(event) {
        const input = event.target.value;
        setNewNickname(input);
    }

    function updateEmail(event) {
        const input = event.target.value;
        setNewEmail(input);
    }

    function updateBio(event) {
        const input = event.target.value;
        setNewBio(input);
    }

    function updatePicURL(event) {
        const input = event.target.value;
        setNewPicURL(input);
    }

    async function sendUpdateUserInfoRequest() {
        const sendNickname = newNickname === "" ? nickname : newNickname;
        const sendEmail = newEmail === "" ? email : newEmail;
        const sendBio = newBio === "" ? bio : newBio;
        const sendPicURL = newPicURL === "" ? picURL : newPicURL;
        const response = sendRequest({requestType: "updateUserInfo", userID: props.currentUserID, nickname: sendNickname, email: sendEmail, bio: sendBio, picURL: sendPicURL})
        if(response) {
            props.showMessage("Profile Updated", "success");
            sendMyProfileRequest();
        }
        else {
            props.showMessage("Error updating profile", "error");
        }
    }

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                <Grid container direction="column" justifyContent="center" alignItems="center">
                    <Grid item className={classes.gridItem}>
                        <TextField
                            className={classes.textField}
                            variant="outlined"
                            onChange={updateNickname}
                            // label="Nickname"
                            placeholder={getNicknamePlaceholder()}
                        />
                    </Grid>
                    <Grid item className={classes.gridItem}>
                        <TextField
                            className={classes.textField}
                            variant="outlined"
                            onChange={updateEmail}
                            // label="Email"
                            placeholder={getEmailPlaceholder()}
                        />
                    </Grid>
                    <Grid item className={classes.gridItem}>
                        <TextField
                            className={classes.textField}
                            variant="outlined"
                            onChange={updateBio}
                            // label="Bio"
                            placeholder={getBioPlaceholder()}
                            multiline
                            maxRows={4}
                        />
                    </Grid>
                    <Grid item>
                        <Button variant="outlined" onClick={sendUpdateUserInfoRequest}>
                            Update My Info
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Container>
    )
}