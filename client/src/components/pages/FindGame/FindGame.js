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

    const gridItemCols = { xs: 12, sm: 10, md: 5, lg: 4 };
    return (
        <div className={classes.root}>
            <Grid container justifyContent="center" spacing={gridSpacing}>
                <Grid className="mr-1" item {...gridItemCols}>
                    <NewGamesTable userID={props.currentUserID} openInvitesModal={openInvitesModal} />
                </Grid>
                <Grid item {...gridItemCols}>
                    <InvitesTable userID={props.currentUserID} />
                </Grid>

            </Grid>
        </div>
    );
}