import React from "react";

import About from "../pages/About/About";
import FindGame from "../pages/FindGame/FindGame";
import Login from "../pages/LoginRegister/Login";
import Play from "../pages/Play/Play";
import Profile from "../pages/Profile/Profile";
import Register from "../pages/LoginRegister/Register";
import Rules from "../pages/Rules/Rules";

import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import GavelIcon from '@material-ui/icons/Gavel';
import InfoIcon from '@material-ui/icons/Info';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';
import SearchIcon from '@material-ui/icons/Search';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import VpnKeyIcon from '@material-ui/icons/VpnKey';

export const PageVisibility = Object.freeze({
    ALL: "all",
    LOGGED_OUT: "logged_out",
    LOGGED_IN: "logged_in",
});

export const pageData = [
    {
        component: Login,
        tabName: "Login",
        tabIcon: <VpnKeyIcon/>,
        visibility: PageVisibility.LOGGED_OUT
    },
    {
        component: Register,
        tabName: "Register",
        tabIcon: <LibraryBooksIcon/>,
        visibility: PageVisibility.LOGGED_OUT
    },
    {
        component: Play,
        tabName: "Play",
        tabIcon: <SportsEsportsIcon/>,
        visibility: PageVisibility.LOGGED_IN
    },
    {
        component: FindGame,
        tabName: "Find Game",
        tabIcon: <SearchIcon/>,
        visibility: PageVisibility.LOGGED_IN
    },
    {
        component: Rules,
        tabName: "Rules",
        tabIcon: <GavelIcon/>,
        visibility: PageVisibility.LOGGED_IN
    },
    {
        component: Profile,
        tabName: "Profile",
        tabIcon: <AccountCircleIcon/>,
        visibility: PageVisibility.LOGGED_IN
    },
    {
        component: About,
        tabName: "About",
        tabIcon: <InfoIcon/>,
        visibility: PageVisibility.ALL
    }
];