import React, {useEffect, useState} from "react";
import { Grid, makeStyles } from "@material-ui/core";
import InvitesTable from "./InvitesTable";
import NewGamesTable from "./NewGamesTable";
import UserInvitesModal from "./UserInvitesModal";
import useIsMountedRef from "../../../utils/useIsMountedRef";
import {sendRequest} from "../../../utils/restfulAPI";

const gridSpacing = 6;

const useStyles = makeStyles({
    root: {
        padding: (gridSpacing * 8) / 2  // fix issue with Grid spacing creating scrollbar
    },
});

export default function FindGame(props) {
    const classes = useStyles();
    const mountedRef = useIsMountedRef();

    const [allGames, setAllGames] = useState([]);
    const [invitesModalInfo, setInvitesModalInfo] = useState({ isOpen: false, gameID: -1 });
    const openInvitesModal = (gameID=-1) => setInvitesModalInfo({ isOpen: true, gameID: gameID });

    useEffect(refreshGames, []);

    function refreshGames() {
        sendGamesRequest(props.currentUserID).then(newGames => {
            if (mountedRef.current)
                setAllGames(newGames);
        });
    }

    return (
        <div className={classes.root}>
            <Grid container justifyContent="center" spacing={gridSpacing}>
                <TableGridItem>
                    <NewGamesTable userID={props.currentUserID} openInvitesModal={openInvitesModal}
                                   showMessage={props.showMessage} allGames={allGames} />
                </TableGridItem>
                <TableGridItem>
                    <InvitesTable userID={props.currentUserID} showMessage={props.showMessage} />
                </TableGridItem>
            </Grid>
            <UserInvitesModal
                userID={props.currentUserID}
                isOpen={invitesModalInfo.isOpen}
                gameID={invitesModalInfo.gameID}
                setGameID={(newGameID) => setInvitesModalInfo({ isOpen: true, gameID: newGameID })}
                closeModal={() => setInvitesModalInfo({ ...invitesModalInfo, isOpen: false })}
                refreshGames={refreshGames}
                showMessage={props.showMessage}
            />
        </div>
    );
}

function TableGridItem(props) {
    return (
        <Grid item xs={12} sm={10} md={5} lg={4}>
            {props.children}
        </Grid>
    );
}

async function sendGamesRequest(userID) {
    const requestBody = { requestType: "game", userID: userID, type: "PENDING" };
    const response = await(sendRequest(requestBody));
    if (!response) {
        console.log(`Error with ${requestBody.requestType} request`)
        return [];
    }
    return response.games;
}