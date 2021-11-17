package com.tco.requests;

import java.sql.SQLIntegrityConstraintViolationException;
import com.tco.misc.BadRequestException;
import com.tco.database.Database;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewInviteRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(NewInviteRequest.class);

    private int userID;
    private int gameID;
    private List<Integer> opponentIDs;

    @Override
    public void buildResponse() throws Exception {
        createInvite();
        log.trace("buildResponse -> {}", this);
    }

    private void createInvite() throws Exception {
        String query = getInsertQuery();
        Object[] queryParameters = getParameters();

        try (Database db = new Database()) {
            db.update(query, queryParameters);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new BadRequestException("An opponent in the list is already invited to this game.\n" + e.getMessage());
        }
    }

    private String getInsertQuery() {
        return "INSERT INTO invites (gameID, sender, receiver, status) VALUES " +
                "(?, ?, ?, ?), ".repeat(Math.max(0, opponentIDs.size() - 1)) +
                "(?, ?, ?, ?)";
    }

    private Object[] getParameters() throws BadRequestException {
        Object[] params = new Object[opponentIDs.size() * 4];

        for (int i = 0; i < opponentIDs.size(); i++) {
            int opponentID = opponentIDs.get(i);

            if (opponentID == this.userID) {
                throw new BadRequestException("An user cannot send an invite to themselves.");
            }

            params[i*4] = this.gameID;
            params[i*4+1] = this.userID;
            params[i*4+2] = opponentID;
            params[i*4+3] = "PENDING";
        }

        return params;
    }
}
