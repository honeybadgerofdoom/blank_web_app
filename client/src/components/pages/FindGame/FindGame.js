import React, {useState} from "react";
import { Grid, makeStyles } from "@material-ui/core";
import InvitesTable from "./InvitesTable";
import NewGamesTable from "./NewGamesTable";
import UserInvitesModal from "./UserInvitesModal";

const gridSpacing = 6;

const useStyles = makeStyles( {
    root: {
        padding: (gridSpacing * 8) / 2  // fix issue with Grid spacing creating scrollbar
    },
});

export default function FindGame(props) {
    const classes = useStyles();

    const [invitesModalInfo, setInvitesModalInfo] = useState({ isOpen: false, gameID: -1 });
    const openInvitesModal = (gameID=-1) => setInvitesModalInfo({ isOpen: true, gameID: gameID });

    return (
        <div className={classes.root}>
            <Grid className="mt-1" container justifyContent="center" spacing={gridSpacing}>
                <TableGridItem className="mr-1">
                    <NewGamesTable userID={props.currentUserID} openInvitesModal={openInvitesModal} showMessage={props.showMessage} />
                </TableGridItem>
                <TableGridItem>
                    <InvitesTable userID={props.currentUserID} showMessage={props.showMessage} />
                </TableGridItem>
                <UserInvitesModal
                    isOpen={invitesModalInfo.isOpen}
                    gameID={invitesModalInfo.gameID}
                    closeModal={() => setInvitesModalInfo({ ...invitesModalInfo, isOpen: false })}
                />
            </Grid>
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