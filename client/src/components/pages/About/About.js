import React from "react";
import {Typography, Card, CardContent, CardMedia, Grid, Item} from "@material-ui/core";
import teamImage from "../../../../../images/t02TeamImage.jpg";
import aaronImage from "../../../../../images/Aaron Lawrence 414 pic.jpg";
import aprilImage from "../../../../../images/april.jpg";
import mattImage from "../../../../../images/matt-selfie.jpg";
import kassidyImage from "../../../../../images/kass_team_image.jpg";
import laniImage from "../../../../../images/LaniB- image.jpg";
import darinImage from "../../../../../images/darin.jpg";
export default function About(props) {
    return (
        <Grid container> 
            <Grid item xs={4}/>
            <Grid item>
            <Typography align="center" variant="h4">About Us</Typography>

            <br/>
            <Grid item>
            <div>
                <Card style={{ width: '30rem' }}>
                    <CardMedia 
                        component="img"
                        image={teamImage}
                        alt="team image"
                    />
                    <CardContent>
                    
                        <Typography align="center" variant="h5">Team ReaKt</Typography>
                        <br />
                        <Typography align="center">Our object is to create a fully functional, interactive online chess application using Agile software development practices.</Typography>
                        <br />

                    </CardContent>
                </Card>
                <Card style={{ width: '30rem' }}>
                <CardMedia 
                        component="img"
                        image={kassidyImage}
                        alt="kassidy image"
                    />
                    <CardContent>
                        <Typography align="center" variant="h5">Kassidy Barram</Typography>
                        <br />
                        <Typography align="center">Kassidy is a senior at Colorado State University and will graduate in Spring of 2022 with a degree in Computer Science. She’s worked on drone stabilization research as well as a committee focusing on women’s inclusion in STEM at CSU. She is a proud member of the software engineering Team Reakt and is looking forward to a great semester.</Typography>
                    </CardContent>
                </Card>
                <Card style={{ width: '30rem' }}>
                <CardMedia 
                        component="img"
                        image={laniImage}
                        alt="lani image"
                    />
                    <CardContent>
                        <Typography align="center" variant="h5">Lani Brooks</Typography>
                        <br />
                        <Typography align="center">Lani is a senior in the Computer Science Department. She also works full time. This is the last class needed for her to obtain her degree. When she is not studying or working, she enjoys traveling, hanging out with friends, cosplay, and hiking.</Typography>
                    </CardContent>
                </Card>
                <Card style={{ width: '30rem' }}>
                <CardMedia 
                        component="img"
                        image={darinImage}
                        alt="darin image"
                    />
                    <CardContent>
                        <Typography align="center" variant="h5">Darin Harter</Typography>
                        <br />
                        <Typography align="center">Darin is a computer science student at CSU studying in his final semester. He took up programming in early high school where he developed small games in Java. He is now a software engineering TA and hoping to attend Georgia Tech for his Master’s in CS. He spends his free time watching Colorado sports, learning piano, petting his dog, reading, and playing Switch.</Typography>
                    </CardContent>
                </Card>
                <Card style={{ width: '30rem' }}>
                <CardMedia 
                        component="img"
                        image={aprilImage}
                        alt="april image"
                    />
                    <CardContent>
                        <Typography align="center" variant="h5">April Kelly</Typography>
                        <br />
                        <Typography align="center">April is a senior in the computer science department and works for Alpha Security in Aspen, CO as a UNIX sysadmin/programmer. Outside of class, she enjoys looking for security vulnerabilities and developing hypochondria from reading random articles on PubMed. She hopes to go to medical school one day.</Typography>
                    </CardContent>
                </Card>
                <Card style={{ width: '30rem' }}>
                <CardMedia 
                        component="img"
                        image={aaronImage}
                        alt="aaron image"
                    />
                    <CardContent>
                        <Typography align="center" variant="h5">Aaron Lawrence</Typography>
                        <br />
                        <Typography align="center">Aaron is a senior majoring in computer science at CSU. A love of video games lead him to learn programming in hopes of being able to create his own games. Since he started learning how to program, he has worked with a number of different languages such as Java, Python, BASIC, and more. In his free time, Aaron enjoys talking to friends and, of course, playing various games.</Typography>
                    </CardContent>
                </Card>
                <Card style={{ width: '30rem' }}>
                <CardMedia 
                        component="img"
                        image={mattImage}
                        alt="matt image"
                    />
                    <CardContent>
                        <Typography align="center" variant="h5">Matt Young</Typography>
                        <br />
                        <Typography align="center">Matt is a senior in the CS Department, works on Dr. Shrideep Pallickara's research project doing UI and web development, and is a UTA for cs314. In his free time Matt likes to play music, ride bicycles, and eat tacos.</Typography>
                    </CardContent>
                </Card>
            </div>
            </Grid>
            </Grid>
        </Grid>
    );
}