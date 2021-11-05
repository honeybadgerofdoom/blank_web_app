import React, {useState} from "react";
import Board from "./Board"
import {sendRequest} from "../../../utils/restfulAPI";
import {Button, List, ListItem, Box} from "@material-ui/core";
import ListItemButton from '@mui/material/ListItemButton';
// import ListItemText from '@mui/material/ListItemText';
// import { FixedSizeList } from 'react-window';

export default function Play(props) {
    const [gameID, setGameID] = useState(null);
    const [allGames, setAllGames] = useState([]);
    console.log({allGames})

    async function sendGameRequest() {
        const gameResponse = await(sendRequest({requestType: "game", userID: props.currentUserID}));
        if(gameResponse) {
            setAllGames(gameResponse.gameIDs);
        }
        else {
            console.log("game request failed");
        }
    }
    // function renderRow(props) {
    //     const { index, style } = props;
    //
    //     return (
    //         <ListItem style={style} key={index} component="div" disablePadding>
    //                 <ListItemText primary={`Item ${index + 1}`} />
    //         </ListItem>
    //     );
    // }
    //
    //
    // export default function VirtualizedList() {
    //     return (
    //         <Box
    //             sx={{ width: '100%', height: 400, maxWidth: 360, bgcolor: 'background.paper' }}
    //         >
    //             <FixedSizeList
    //                 height={400}
    //                 width={360}
    //                 itemSize={46}
    //                 itemCount={200}
    //                 overscanCount={5}
    //             >
    //                 {renderRow}
    //             </FixedSizeList>
    //         </Box>
    //     );
    // }
    return<>
        <Button variant="outlined" size="large" onClick={sendGameRequest}>Send that game</Button>
        <Board currentUserID={props.currentUserID} showMessage={props.showMessage} chosenGame={allGames[1]} />
        </>
}