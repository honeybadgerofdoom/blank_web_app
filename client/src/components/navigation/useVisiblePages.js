import { useEffect, useState } from "react";
import { pageData, PageVisibility } from "./pageData";

export default function useVisiblePages(userAuthenticated) {
	const [pages, setPages] = useState(getVisiblePages());

	function getVisiblePages() {
		return pageData.filter(page => 
			(page.visibility === PageVisibility.ALL) ||
			(userAuthenticated && page.visibility === PageVisibility.LOGGED_IN) ||
			(!userAuthenticated && page.visibility === PageVisibility.LOGGED_OUT)
		);
	};

	useEffect(() => {
		setPages(getVisiblePages());
	}, [userAuthenticated]);

	return [pages];
}