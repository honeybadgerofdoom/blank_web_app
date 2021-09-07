import React from "react";
import {Grid, makeStyles, Paper, Typography} from "@material-ui/core";
import ToDoList from "./ToDoList";
import LanguageIcon from '@material-ui/icons/Language';

const useStyles = makeStyles((theme) => ({
	root: {
		margin: theme.spacing(3),
		padding: theme.spacing(3),
	},
	formControl: {
		margin: theme.spacing(3),
	},
}));

export default function Page() {
	const classes = useStyles();
	const toDoList =
		[["Initial 314 Code Gut", true],
		["Clean Console Output", true],
		["Clean Up Client Tests", false],
		["Clean Up Client Files", false],
		["Clean Up Server Tests", false],
		["Clean Up Server Files", false],
		["Clean Up Dependencies", false],
		["Test entry.js", false],
		["Create README.md", false],
		["Update Design.md (let's use one!)", false],
		["Generalize Existing API Endpoint", false]];

	return (
		<Grid
			container
			direction="column"
			justifyContent="center"
			alignItems="center"
		>
			{styledPageItem(
				<Typography align="center"><LanguageIcon/>&nbsp;&nbsp;This is a landing page representing the client side of our web app project</Typography>)
			}
			{styledPageItem(
				<ToDoList list={toDoList}/>
			)}
		</Grid>
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
