import React from "react";
import MyUserInfo from "./MyUserInfo";
import ViewOtherUsers from "./ViewOtherUsers";
import {Grid} from "@material-ui/core";


export default function Profile(props) {
    return (
        <Grid container direction="row" justifyContent="center" alignItems="flex-start">
            <MyUserInfo showMessage={props.showMessage} currentUserID={props.currentUserID} />
            <ViewOtherUsers showMessage={props.showMessage} currentUserID={props.currentUserID} />
        </Grid>
    )
}