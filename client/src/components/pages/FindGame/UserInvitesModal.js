import React, {useEffect, useState} from "react";
import { Button, Checkbox, CircularProgress, Grid, List, ListItem, makeStyles, TextField, Typography } from "@material-ui/core";
import { Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import OtherUser from "../Profile/OtherUser";
import {sendRequest} from "../../../utils/restfulAPI";
import CatchingPokemon from "@mui/icons-material/CatchingPokemon";

const useStyles = makeStyles( {
    invitedUserList: {
        maxHeight: "205px",
        overflow: "auto",
        marginBottom: "20px"
    },
    userList: {
        maxHeight: "270px",
        overflow: "auto"
    },
});

export default function UserInvitesModal(props) {
    const [allUsers, setAllUsers] = useState([]);
    const [users, setUsers] = useState([]);
    const [invitedUserIDs, setInvitedUserIDs] = useState([]);
    const [pendingUserIDs, setPendingUserIDs] = useState(new Set());

    useEffect(() => {
        if (props.isOpen) {
            updateUsersWithFilter("");
            setPendingUserIDs(new Set());
        }
    }, [props.isOpen]);

    function updateUsersWithFilter(searchInput) {
        sendUsersRequest(searchInput, props.currentUserID).then(newUsers => {
            setUsers(newUsers);
            if (searchInput === "")
                setAllUsers(newUsers);
        });
    }

    useEffect(() => {
        if (props.gameID > 0) {
            sendInvitedUsersRequest(props.gameID).then(result => setInvitedUserIDs(result));
        }
    }, [props.gameID]);

    return (
        <Modal isOpen={props.isOpen} toggle={props.closeModal}>
            <ModalHeader> Invite Users to Play! </ModalHeader>
            <UserSearchBody users={users} allUsers={allUsers} updateUsersWithFilter={updateUsersWithFilter} gameID={props.gameID}
                            invitedUserIDs={invitedUserIDs} pendingUserIDs={pendingUserIDs} setPendingUserIDs={setPendingUserIDs} />
            <UserSearchFooter closeModal={props.closeModal} pendingUserIDs={pendingUserIDs} />
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
            <InvitedUsersList {...props} />
            <Typography className="mb-3" variant="h6" component="div">Send New Invites</Typography>
            <TextField
                className="mb-2"
                onChange={handleFilterChanged}
                placeholder="Enter username"
                variant="outlined"
                size="small"
                fullWidth
            />
            <UsersList {...props} />
        </ModalBody>
    );
}

function InvitedUsersList(props) {
    const classes = useStyles();

    if (props.invitedUserIDs.length === 0) {
        return null;
    }

    const invitedUsers = props.allUsers.filter(user => props.invitedUserIDs.includes(user.userID));

    return (
        <div>
            <h6>Currently Invited:</h6>
            <List className={classes.invitedUserList}>
                {invitedUsers.map((user, index) =>
                    <ListItem key={index}>
                        <Grid container justifyContent="center">
                            <Grid item xs={2}>
                                <Checkbox color="primary" checkedIcon={<CatchingPokemon/>} checked={true} />
                            </Grid>
                            <Grid item xs={10}>
                                <OtherUser user={user} />
                            </Grid>
                        </Grid>
                    </ListItem>
                )}
            </List>
        </div>
    );
}

function UsersList(props) {
    const classes = useStyles();

    if (props.users.length === 0) {
        return <Typography className="mt-1 text-center" variant="body1" component="div">
            No matching users.
        </Typography>
    }

    return (
        <List className={classes.userList}>
            {nonInvitedUsers.map((user, index) =>
                <ListItem key={index}>
                    <Grid container justifyContent="center">
                        <Grid item xs={2}>
                            <Checkbox onChange={event => handleCheckboxChanged(event, user.userID)} />
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

function UserSearchFooter(props) {
    function handleSendInvites() {
        console.log(props.pendingUserIDs);
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