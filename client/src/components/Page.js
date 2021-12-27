import React from "react";
import {makeStyles, Typography} from "@material-ui/core";

const useStyles = makeStyles(() => ({
	root: {
		margin: "10px"
	}
}));

export default function Page(props) {
	const classes = useStyles();

	return (
		<Typography align="center" className={classes.root}>Blank Page</Typography>
	);
}
