import React, {useState} from "react";
import {Button, ButtonGroup, Grid, makeStyles, Typography} from "@material-ui/core";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import InfoIcon from '@material-ui/icons/Info';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import VpnKeyIcon from '@material-ui/icons/VpnKey';
import SearchIcon from '@material-ui/icons/Search';
import About from "./SitePages/About";
import Profile from "./SitePages/Profile";
import FindGame from "./SitePages/FindGame";
import Play from "./SitePages/Play";

const useStyles = makeStyles((theme) => ({
	root: {
		flexGrow: 1,
		width: '100%',
		overflowX: "hidden",
	},
	buttonSpacing: {
		margin: theme.spacing(3),
	},
	tabs: {
		borderBottom: '2px solid #adadad',
	},
}));

export default function Page() {
	const classes = useStyles();

	const tabSwitchingStyles = [[{display: 'block'}, 'contained', 'primary'], [{display: 'none'}, 'outlined', '']];

	const [loginPageStyles, setLoginPageStyles] = useState(tabSwitchingStyles[0]);
	const [profilePageStyles, setProfilePageStyles] = useState(tabSwitchingStyles[1]);
	const [matchFindingPageStyles, setMatchFindingPageStyles] = useState(tabSwitchingStyles[1]);
	const [gamePageStyles, setGamePageStyles] = useState(tabSwitchingStyles[1]);
	const [aboutPageStyles, setAboutPageStyles] = useState(tabSwitchingStyles[1]);

	const setterArray = [setLoginPageStyles, setProfilePageStyles, setMatchFindingPageStyles, setGamePageStyles, setAboutPageStyles];

	function switchTabs(index) {
		setterArray.forEach((setter, internalIndex) => {
			if(index === internalIndex) {
				setter(tabSwitchingStyles[0]);
			}
			else {
				setter(tabSwitchingStyles[1]);
			}
		});
	}

	return (
		<div className={classes.root}>
			<div className={classes.tabs}>
				<Grid
					container
					spacing={3}
					justifyContent="center"
					alignItems="center"
				>
					<Grid item>
						<ButtonGroup className={classes.buttonSpacing} size="large">
							<Button variant={loginPageStyles[1]} color={loginPageStyles[2]} startIcon={<VpnKeyIcon/>} onClick={() => switchTabs(0)}>Log In</Button>
							<Button variant={gamePageStyles[1]} color={gamePageStyles[2]} startIcon={<SportsEsportsIcon/>} onClick={() => switchTabs(3)}>Play</Button>
							<Button variant={matchFindingPageStyles[1]} color={matchFindingPageStyles[2]} startIcon={<SearchIcon/>} onClick={() => switchTabs(2)}>Find A Game</Button>
							<Button variant={profilePageStyles[1]} color={profilePageStyles[2]} startIcon={<AccountCircleIcon/>} onClick={() => switchTabs(1)}>Profile</Button>
							<Button variant={aboutPageStyles[1]} color={aboutPageStyles[2]} startIcon={<InfoIcon/>} onClick={() => switchTabs(4)}>About</Button>
						</ButtonGroup>
					</Grid>
				</Grid>
			</div>

			<br/>

			{pageSection(loginPageStyles, <About />)}
			{pageSection(profilePageStyles, <Profile />)}
			{pageSection(matchFindingPageStyles, <FindGame />)}
			{pageSection(gamePageStyles, <Play />)}
			{pageSection(aboutPageStyles, <About />)}
		</div>
	)
}

function pageSection(styles, component) {
	return (
		<div style={styles[0]}>
			{component}
		</div>
	)
}

