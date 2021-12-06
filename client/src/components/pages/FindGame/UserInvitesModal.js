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
        sendUsersRequest(searchInput, props.userID).then(newUsers => {
            setUsers(newUsers);
            if (searchInput === "")
                setAllUsers(newUsers);
        });
    }

    useEffect(() => {
        if (props.gameID >= 0) {
            updateInvitedUsers();
        } else if (props.isOpen) {
            sendNewGameRequest(props.userID).then(newGameID => {
                props.setGameID(newGameID);
                props.refreshGames();
            });
        }
    }, [props.gameID]);

    function updateInvitedUsers() {
        sendInvitedUsersRequest(props.gameID).then(result => setInvitedUserIDs(result));
    }

    return (
        <Modal isOpen={props.isOpen} toggle={props.closeModal}>
            <ModalHeader> Invite Users to Play! </ModalHeader>
            <UserSearchBody users={users} allUsers={allUsers} updateUsersWithFilter={updateUsersWithFilter} gameID={props.gameID}
                            invitedUserIDs={invitedUserIDs} pendingUserIDs={pendingUserIDs} setPendingUserIDs={setPendingUserIDs} />
            <UserSearchFooter pendingUserIDs={pendingUserIDs} updateInvitedUsers={updateInvitedUsers} {...props} />
        </Modal>
    );
}

async function sendNewGameRequest(userID, showMessage) {
    const newGameResponse = await sendRequest({ requestType: "newGame", userID: userID });
    if (newGameResponse && !newGameResponse.success) {
        showMessage("Failed to create a new game.", "error");
        return -1;
    }
    return newGameResponse.gameID;
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

    function handleCheckboxChanged(event, userID) {
        const newIDSet = new Set(props.pendingUserIDs);
        if (event.target.checked) {
            newIDSet.add(userID);
        } else {
            newIDSet.delete(userID);
        }
        props.setPendingUserIDs(newIDSet);
    }

    const nonInvitedUsers = props.users.filter(user => !props.invitedUserIDs.includes(user.userID));

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
    async function handleSendInvites() {
        await sendNewInvitesRequest(props.gameID, props.userID, props.pendingUserIDs, props.showMessage, props.refreshGames);
        props.updateInvitedUsers();
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

async function sendNewInvitesRequest(gameID, userID, opponentIDSet, showMessage, refreshGames) {
    const usersToInvite = Array.from(opponentIDSet);
    const requestBody = { requestType: "newInvite", userID: userID, gameID: gameID, opponentIDs: usersToInvite };
    const response = await sendRequest(requestBody);
    if (!response) {
        showMessage(`Failed to invite users to game #${gameID}.`, "error");
        return;
    }
    const usersText = (usersToInvite.length === 1) ? "user" : "users";
    showMessage(`Invited ${usersToInvite.length} ${usersText} to game #${gameID}.`, "success");
    refreshGames();
}

async function sendUsersRequest(input, userID) {
    const requestBody = { requestType: "users", match: input, limit: 0, excludeID: userID };
    return sendRequestForKey(requestBody, "users");
}

async function sendInvitedUsersRequest(gameID) {
    const requestBody = { requestType: "invitedUsers", "gameID": gameID };
    return sendRequestForKey(requestBody, "invitedUserIDs");
}

async function sendRequestForKey(requestBody, resultKey) {
    const response = await sendRequest(requestBody);
    if (!response) {
        return [];
    }
    return response[resultKey];
}