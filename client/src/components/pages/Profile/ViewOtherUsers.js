import React, {useEffect, useState} from "react";
import {Container, List, makeStyles, Paper, TextField, ListItem, Modal} from "@material-ui/core";
import {sendRequest} from "../../../utils/restfulAPI";
import OtherUserModal from "./OtherUserModal";

const useStyles = makeStyles( {
    root: {
        margin: "20px",
        padding: "20px",
        overflow: "auto",
        maxHeight: "55vh",
    },
    textField: {
        width: "100%",
    }
});

export default function ViewOtherUsers(props) {
    const classes = useStyles();
    const [users, setUsers] = useState([]);

    useEffect(() => {
        sendUsersRequest("");
    }, []);

     async function sendUsersRequest(input) {
         const response = await sendRequest({requestType: "users", match: input, limit: 0});
         if(response) {
             setUsers(response.users);
         }
         else {
             console.log("Error sending users request");
         }
     }

     function handleInput(event) {
         const input = event.target.value;
         sendUsersRequest(input);
     }

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} className={classes.root}>
                <TextField
                    variant="outlined"
                    className={classes.textField}
                    placeholder="Search for Users..."
                    onChange={handleInput}
                />
                <List>
                    {users.map((user, index) => {
                        return (
                            <ListItem key={index}>
                                <OtherUserModal user={user} />
                            </ListItem>
                        )
                    })}
                </List>
            </Paper>
        </Container>
    )
}