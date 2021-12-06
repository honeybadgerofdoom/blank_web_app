import React from "react";
import {Button, makeStyles, Typography} from "@material-ui/core";
import TabNavigator from "./navigation/TabNavigator";
import useVisiblePages from "./navigation/useVisiblePages";
import useStoredState from "../utils/useStoredState";

const useStyles = makeStyles(() => ({
	root: {
		flexGrow: 1,
		width: '100%',
		overflowX: 'hidden',
	},
	userText: {
		position: "absolute",
		top: 0,
		right: 0,
		fontWeight: 600
	}
}));

export default function Page(props) {
	const classes = useStyles();
	const [currentPageIndex, setCurrentPageIndex] = useStoredState("currentPageIndex", 0);
	const [userAuthenticated, setUserAuthenticated] = useStoredState("userAuthenticated", false);
	const [currentUserID, setCurrentUserID] = useStoredState("currentUserID", null);
	const [currentUsername, setCurrentUsername] = useStoredState("currentUsername", "");
	const [visiblePages] = useVisiblePages(userAuthenticated);

	function signOut() {
		setUserAuthenticated(false);
		setCurrentPageIndex(0);
	}

	function signoutButton() {
		return userAuthenticated
			? <Button className="position-absolute" onClick={signOut}>Sign Out</Button>
			: null;
	}

	function userText() {
		return userAuthenticated
			? <Typography variant="button" className={classes.userText + " mt-2 me-3"}>{currentUsername}</Typography>
			: null;
	}

	return (
		<div className={classes.root}>
			{signoutButton()}
			{userText()}

			<TabNavigator 
				currentPageIndex={currentPageIndex}
				visiblePages={visiblePages}
				setCurrentPageIndex={setCurrentPageIndex} 
				userAuthenticated={userAuthenticated}
			/>
			
			<br />
			
			<div>
				{React.createElement(visiblePages[currentPageIndex].component,
					{
						setUserAuthenticated: setUserAuthenticated,
						showMessage: props.showMessage,
						setCurrentUserID: setCurrentUserID,
						currentUserID: currentUserID,
						setCurrentUsername: setCurrentUsername
					}
				)}
			</div>
		</div>
	);
}
