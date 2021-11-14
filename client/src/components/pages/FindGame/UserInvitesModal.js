import React, {useEffect, useState} from "react";
import { Button, Checkbox, CircularProgress, Grid, LinearProgress, List, ListItem, makeStyles, TextField, Typography } from "@material-ui/core";
import { Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import OtherUser from "../Profile/OtherUser";
import {sendRequest} from "../../../utils/restfulAPI";
import { CatchingPokemon, CatchingPokemonOutlined } from "@mui/icons-material";

const useStyles = makeStyles( {
    userList: {
        maxHeight: "400px",
        overflow: "auto"
    },
});

export default function UserInvitesModal(props) {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        if (props.isOpen) {
            updateUsersWithFilter("");
        }
    }, [props.isOpen]);

    function updateUsersWithFilter(searchInput) {
        sendUsersRequest(searchInput, props.currentUserID).then(newUsers => {
            //newUsers.sort((users, index) => index % 3 === 0 ? 1 : -1);
            setUsers(newUsers);
        });
    }

    return (
        <Modal isOpen={props.isOpen} toggle={props.closeModal}>
            <ModalHeader> Invite Users to Play! </ModalHeader>
            <UserSearchBody users={users} updateUsersWithFilter={updateUsersWithFilter} gameID={props.gameID}/>
            <UserSearchFooter closeModal={props.closeModal} />
        </Modal>
    );
}

function UserSearchBody(props) {
    function handleFilterChanged(event) {
        const newInput = event.target.value;
        props.updateUsersWithFilter(newInput);
    }

    return (
        <ModalBody>
            <Typography className="mb-3" variant="h6" component="div">
                Match ID: {props.gameID !== -1 ? props.gameID : <CircularProgress size={20}/>}
            </Typography>
            <TextField
                className="mb-3"
                onChange={handleFilterChanged}
                placeholder="Enter username"
                variant="outlined"
                size="small"
                fullWidth
            />

        </ModalBody>
    );
}

function UserSearchFooter(props) {
    function handleSendInvites() {
        props.closeModal();
    }

    return (
        <ModalFooter>
            <Button color="primary" onClick={handleSendInvites}>Send Invites</Button>
            {' '}
            <Button color="secondary" onClick={props.closeModal}>Cancel</Button>
        </ModalFooter>
    );
}

async function sendUsersRequest(input, userID) {
    const requestBody = { requestType: "users", match: input, limit: 0, excludeID: userID };
    const response = await sendRequest(requestBody);
    if (!response) {
        console.log("Error sending users request");
        return [];
    }
    return response.users;
}