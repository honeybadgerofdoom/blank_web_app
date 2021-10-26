import React, { useState } from "react";
import { Button, makeStyles } from "@material-ui/core";
import TabNavigator from "./navigation/TabNavigator";
import useVisiblePages from "./navigation/useVisiblePages";

const useStyles = makeStyles((theme) => ({
	root: {
		flexGrow: 1,
		width: '100%',
		overflowX: 'hidden',
	}
}));

export default function Page(props) {
	const classes = useStyles();
	const [currentPageIndex, setCurrentPageIndex] = useState(0);
	const [userAuthenticated, setUserAuthenticated] = useState(false);
	const [currentUserID, setCurrentUserID] = useState(null);
	console.log({currentUserID})
	const [visiblePages] = useVisiblePages(userAuthenticated);

	function signOut() {
		setUserAuthenticated(false);
		setCurrentPageIndex(0);
	}

	function signoutButton() {
		return userAuthenticated ? <Button className="position-absolute" onClick={signOut}>{'Sign Out'}</Button> : null;
	}

	return (
		<div className={classes.root}>
			{signoutButton()}

			<TabNavigator 
				currentPageIndex={currentPageIndex}
				visiblePages={visiblePages}
				setCurrentPageIndex={setCurrentPageIndex} 
				userAuthenticated={userAuthenticated}
			/>
			
			<br />
			
			<div>
				{React.createElement(visiblePages[currentPageIndex].component, {setUserAuthenticated: setUserAuthenticated, showMessage: props.showMessage, setCurrentUserID: setCurrentUserID, currentUserID: currentUserID})}
			</div>
		</div>
	);
}
