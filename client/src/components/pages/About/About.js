import React from "react";
import {Typography, Card, CardContent, CardMedia, Grid, Container, makeStyles} from "@material-ui/core";
import { team, members } from "./aboutInfo";

const gridSpacing = 4;

const useStyles = makeStyles({
    root: {
        padding: (gridSpacing * 8) / 2  // fix issue with Grid spacing creating scrollbar
    },
    bold: {
        fontWeight: 600
    },
    card: {
        border: "3px solid rgba(60, 60, 60, 0.9)",
        padding: "10px",
        boxShadow: "rgba(0, 0, 0, 0.35) 0px 5px 15px",
    }
});

export default function About() {
    const classes = useStyles();
    return (
        <Container className={classes.root}>
            <Grid container justifyContent="center" spacing={4}>
                <TeamCard />
            </Grid>
            <Grid container justifyContent="center" spacing={4}>
                <MemberCards />
            </Grid>
        </Container>
    );
}

function TeamCard() {
    const classes = useStyles();
    return (
        <Grid item xs={12} sm={8} md={6} lg={5} className="mb-3">
            <Card className={classes.card}>
                <CardMedia component="img" image={team.picture} alt={team.picAlt} />
                <CardContent>
                    <Typography className={classes.bold} align="center" variant="h4">{team.name}</Typography>
                    <br />
                    <Typography align="center">{team.missionStatement}</Typography>
                </CardContent>
            </Card>
        </Grid>
    );
}

function MemberCards() {
    const classes = useStyles();
    //const membersToRender = members.filter((member, index) => props.indices.includes(index));
    return (
        members.map(member =>
            <Grid item xs={12} sm={6} md={4} lg={3} key={member.name}>
                <Card className={classes.card}>
                    <CardMedia
                        component="img"
                        image={member.picture}
                        alt={member.picAlt}
                    />
                    <CardContent>
                        <Typography className={classes.bold} align="center" variant="h5">{member.name}</Typography>
                        <br />
                        <Typography align="center">{member.bio}</Typography>
                    </CardContent>
                </Card>
            </Grid>
        )
    );
}