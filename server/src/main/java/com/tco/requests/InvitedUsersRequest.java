package com.tco.requests;

import com.tco.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvitedUsersRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(InvitedUsersRequest.class);

    private int gameID;
    private List<Integer> invitedUserIDs;

    @Override
    public void buildResponse() throws Exception {
        this.invitedUserIDs = getInvitedUserIDList(gameID);

        log.trace("buildResponse -> {}", this);
    }

    private List<Integer> getInvitedUserIDList(int gameID) throws Exception {
        String query = "SELECT receiver FROM invites WHERE gameID=?";

        List<Integer> userIDs = new ArrayList<>();
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(query, gameID);

            for (Map<String, String> resultRow : results) {
                int receiverID = Integer.parseInt(resultRow.get("receiver"));
                userIDs.add(receiverID);
            }
        }

        return userIDs;
    }
}
