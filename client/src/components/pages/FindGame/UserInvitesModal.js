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
    const [invitedUserIDs, setInvitedUserIDs] = useState([]);

    useEffect(() => {
        if (props.isOpen) {
            updateUsersWithFilter("");
        }
    }, [props.isOpen]);

    function updateUsersWithFilter(searchInput) {
        sendUsersRequest(searchInput, props.currentUserID).then(newUsers => setUsers(newUsers));
    }

    useEffect(() => {
        if (props.gameID > 0) {
            sendInvitedUsersRequest(props.gameID).then(result => setInvitedUserIDs(result));
        }
    }, [props.gameID]);

    return (
        <Modal isOpen={props.isOpen} toggle={props.closeModal}>
            <ModalHeader> Invite Users to Play! </ModalHeader>
            <UserSearchBody users={users} updateUsersWithFilter={updateUsersWithFilter} gameID={props.gameID} invitedUserIDs={invitedUserIDs} />
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
            <UsersList users={props.users} invitedUserIDs={props.invitedUserIDs} />
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

function UsersList(props) {
    const classes = useStyles();

    if (props.users.length === 0) {
        return <LinearProgress />;
    }
    return (
        <List className={classes.userList}>
            {props.users.map((user, index) =>
                <ListItem key={index}>
                    <Grid container justifyContent="center">
                        <Grid item xs={2}>
                            <Checkbox icon={<CatchingPokemonOutlined/>} checkedIcon={<CatchingPokemon/>} defaultChecked={true} />
                        </Grid>
                        <Grid item xs={10}>
                            <OtherUser user={user} />
                        </Grid>
                    </Grid>
                </ListItem>
            )}
        </List>
    );
}

async function sendUsersRequest(input, userID) {
    const requestBody = { requestType: "users", match: input, limit: 0, excludeID: userID };
    return sendRequestForList(requestBody, "users");
}

async function sendInvitedUsersRequest(gameID) {
    const requestBody = { requestType: "invitedUsers", "gameID": gameID };
    return sendRequestForList(requestBody, "invitedUserIDs");
}

async function sendRequestForList(requestBody, resultKey) {
    const response = await sendRequest(requestBody);
    if (!response) {
        return [];
    }
    return response[resultKey];
}