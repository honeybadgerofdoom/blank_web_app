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
	const [visiblePages] = useVisiblePages(userAuthenticated);

	function toggleSignIn() {
		setUserAuthenticated(!userAuthenticated);
		setCurrentPageIndex(0);
	}

	const pageProps = {
		showMessage: props.showMessage,
		setUserAuthenticated: setUserAuthenticated
	}

	return (
		<div className={classes.root}>
			<Button className="position-absolute" onClick={toggleSignIn}>
				{userAuthenticated ? 'Sign Out' : 'Sign In'}
			</Button>

			<TabNavigator 
				currentPageIndex={currentPageIndex}
				visiblePages={visiblePages}
				setCurrentPageIndex={setCurrentPageIndex} 
				userAuthenticated={userAuthenticated}
			/>
			
			<br />
			
			<div className={classes.page}>
				{React.createElement(visiblePages[currentPageIndex].component, {pageProps})}
			</div>
		</div>
	);
}
