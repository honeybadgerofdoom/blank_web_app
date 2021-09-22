import React from "react";
import { Button, ButtonGroup, Grid, makeStyles } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
	buttonSpacing: {
		margin: theme.spacing(3),
	},
	tabs: {
		borderBottom: '2px solid #adadad',
	},
}));

export default function TabNavigator(props) {
    const classes = useStyles();

	return (
	    <div className={classes.tabs}>
            <Grid container spacing={3} justifyContent="center" alignItems="center">
                <Grid item>
                    <NavigationButtons {...props} />
                </Grid>
            </Grid>
        </div>
	);
}

function NavigationButtons(props) {
    const classes = useStyles();

    return (
        <ButtonGroup className={classes.buttonSpacing} size="large">
            {props.visiblePages.map((page, index) => {
                const tabSelected = props.currentPageIndex === index;
                return (
                    <Button 
                        key={page.tabName}
                        variant={tabSelected ? 'contained' : 'outlined'}
                        color={tabSelected ? 'primary' : ''}
                        startIcon={page.tabIcon}
                        onClick={() => props.setCurrentPageIndex(index)}
                    >
                        {page.tabName}
                    </Button>    
                );
            })}
        </ButtonGroup>
    );
}