import React, {useState} from "react";
import {Button, ButtonGroup, Grid, makeStyles, Paper, Typography} from "@material-ui/core";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import InfoIcon from '@material-ui/icons/Info';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import VpnKeyIcon from '@material-ui/icons/VpnKey';
import SearchIcon from '@material-ui/icons/Search';

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

	const tabSwitchingStyles = [[{display: 'block'}, 'contained', 'primary', false], [{display: 'none'}, 'outlined', '', false], [{display: 'none'}, 'outlined', '', true]];
	const [loginPageStyles, setLoginPageStyles] = useState(tabSwitchingStyles[0]);
	const [aboutPageStyles, setAboutPageStyles] = useState(tabSwitchingStyles[1]);
	const [matchFindingPageStyles, setMatchFindingPageStyles] = useState(tabSwitchingStyles[2]);
	const [profilePageStyles, setProfilePageStyles] = useState(tabSwitchingStyles[2]);
	const [gamePageStyles, setGamePageStyles] = useState(tabSwitchingStyles[2]);

	function switchTabs(index) {
		if(index === 0) {
			setLoginPageStyles(tabSwitchingStyles[0]);
			setAboutPageStyles(tabSwitchingStyles[1]);
		}
		else if(index === 1) {
			setLoginPageStyles(tabSwitchingStyles[1]);
			setAboutPageStyles(tabSwitchingStyles[0]);
		}
		else {}
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
							<Button variant={loginPageStyles[1]} color={loginPageStyles[2]} startIcon={<VpnKeyIcon/>} disabled={loginPageStyles[3]} onClick={() => switchTabs(0)}>Log In</Button>
							<Button variant={profilePageStyles[1]} color={profilePageStyles[2]} startIcon={<AccountCircleIcon/>} disabled={profilePageStyles[3]} onClick={() => switchTabs(2)}>Profile</Button>
							<Button variant={matchFindingPageStyles[1]} color={matchFindingPageStyles[2]} startIcon={<SearchIcon/>} disabled={matchFindingPageStyles[3]}  onClick={() => switchTabs(2)}>Find A Game</Button>
							<Button variant={gamePageStyles[1]} color={gamePageStyles[2]} startIcon={<SportsEsportsIcon/>} disabled={gamePageStyles[3]}  onClick={() => switchTabs(2)}>Play</Button>
							<Button variant={aboutPageStyles[1]} color={aboutPageStyles[2]} startIcon={<InfoIcon/>} disabled={aboutPageStyles[3]}  onClick={() => switchTabs(1)}>About</Button>
						</ButtonGroup>
					</Grid>
				</Grid>
			</div>

			<br/>
			<div style={loginPageStyles[0]}>
				<Typography>Login Page</Typography>
			</div>
			<div style={profilePageStyles[0]}>
				<Typography>Profile</Typography>
			</div>
			<div style={matchFindingPageStyles[0]}>
				<Typography>Find A Game</Typography>
			</div>
			<div style={gamePageStyles[0]}>
				<Typography>Play</Typography>
			</div>
			<div style={aboutPageStyles[0]}>
				<Typography>About Page</Typography>
			</div>
		</div>
	)
}

