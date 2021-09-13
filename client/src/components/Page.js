import React, {useState} from "react";
import {Button, ButtonGroup, Grid, makeStyles, Paper, Typography} from "@material-ui/core";
import PersonIcon from '@material-ui/icons/Person';
import InfoIcon from '@material-ui/icons/Info';

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
	const [aboutPageStyles, setAboutPageStyles] = useState(tabSwitchingStyles[1]);

	function switchTabs(index) {
		if(index === 0) {
			setLoginPageStyles(tabSwitchingStyles[0]);
			setAboutPageStyles(tabSwitchingStyles[1]);
		}
		else if(index === 1) {
			setLoginPageStyles(tabSwitchingStyles[1]);
			setAboutPageStyles(tabSwitchingStyles[0]);
		}
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
							<Button variant={loginPageStyles[1]} color={loginPageStyles[2]} startIcon={<PersonIcon/>} onClick={() => switchTabs(0)}>Data Exploration</Button>
							<Button variant={aboutPageStyles[1]} color={aboutPageStyles[2]} startIcon={<InfoIcon/>} onClick={() => switchTabs(1)}>Modeling</Button>
						</ButtonGroup>
					</Grid>
				</Grid>
			</div>

			<br/>
			<div style={loginPageStyles[0]}>
				<Typography>Login Page</Typography>
			</div>
			<div style={aboutPageStyles[0]}>
				<Typography>About Page</Typography>
			</div>
		</div>
	)

	function styledPageItem(content) {
		return (
			<Grid item>
				<Paper className={classes.root} elevation={3}>
					{content}
				</Paper>
			</Grid>
		)
	}
}

