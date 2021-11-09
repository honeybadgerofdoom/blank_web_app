import React, {useEffect, useState} from "react";
import {Button, Container, FormLabel, Grid, makeStyles, Paper, TextField} from "@material-ui/core";
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
            setBio(response.bio === undefined? "" : response.bio);
            setPicURL(response.picURL === undefined ? "" : response.picURL);
        }
    }

    async function sendUpdateUserInfoRequest() {
        const sendNickname = newNickname === "" ? nickname : newNickname;
        const sendEmail = newEmail === "" ? email : newEmail;
        const sendBio = newBio === "" ? bio : newBio;
        const sendPicURL = newPicURL === "" ? picURL : newPicURL;
        const response = await sendRequest({requestType: "updateUserInfo", userID: props.currentUserID, nickname: sendNickname, email: sendEmail, bio: sendBio, picURL: sendPicURL})
        if(response.success) {
            props.showMessage("Profile Updated", "success");
        }
        else {
            props.showMessage("Error updating profile", "error");
        }
    }

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                <Grid container direction="column" justifyContent="center" alignItems="center">
                    {getTextField(nickname, "username")}
                    {getTextField(email, "email")}
                    {getTextField(bio, "bio")}
                    <Grid item>
                        <Button variant="outlined" onClick={sendUpdateUserInfoRequest}>
                            Update My Info
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Container>
    )

    function getTextField(placeholder, title) {
        return (
            <Grid item className={classes.gridItem}>
                <FormLabel>{title}</FormLabel>
                <TextField
                    className={classes.textField}
                    onChange={(event) => handleUserInput(event, title)}
                    placeholder={placeholder}
                />
            </Grid>
        )
    }

    function handleUserInput(event, textField) {
        const input = event.target.value;
        switch (textField) {
            case "username": setNewNickname(input); break;
            case "email": setNewEmail(input); break;
            case "bio": setNewBio(input); break;
            default: console.log("Error in handleUserInput");
        }
    }

}