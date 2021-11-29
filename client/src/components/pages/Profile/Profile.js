import React from "react";
import MyUserInfo from "./MyUserInfo";
import ViewOtherUsers from "./ViewOtherUsers";
import {Grid} from "@material-ui/core";
import MyStats from "./MyStats";


export default function Profile(props) {
    return (
        <Grid container direction="row" justifyContent="center" alignItems="flex-start">
            <Grid item>
                <Grid container direction="column" justifyContent="center" alignItems="flex-start">
                    <MyUserInfo showMessage={props.showMessage} currentUserID={props.currentUserID} />
                    <MyStats currentUserID={props.currentUserID} />
                </Grid>
            </Grid>
            <Grid item>
                <ViewOtherUsers showMessage={props.showMessage} currentUserID={props.currentUserID} />
            </Grid>
        </Grid>
    )
}