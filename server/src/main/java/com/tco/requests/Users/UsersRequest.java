package com.tco.requests.Users;

import com.tco.database.Database;
import com.tco.requests.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class UsersRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(UsersRequest.class);
    private final static transient int DEFAULT_LIMIT = 50;

    private String match;
    private Integer excludeID;
    private Integer limit;
    private Integer found;
    private List<User> users;

    @Override
    public void buildResponse() {
        String wildcardMatch = "%" + match + "%";
        try (Database db = new Database()) {
            this.users = getUsers(db, wildcardMatch);
            this.found = countUsers(db, wildcardMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.trace("buildResponse -> {}", this);
    }

    public List<User> getUsers(Database db, String wildcardMatch) throws SQLException {
        int actualLimit = (limit > 0) ? limit : DEFAULT_LIMIT;

        Object[] args = (excludeID == null)
                ? new Object[]{ wildcardMatch, actualLimit }
                : new Object[]{ wildcardMatch, excludeID, actualLimit };

        List<Map<String, String>> results = db.query(getUsersQuery(), args);

        List<User> ret = new ArrayList<>();
        for (Map<String, String> userRow : results) {
            int userID = Integer.parseInt(userRow.get("userID"));
            String username = userRow.get("nickname");
            String bio = userRow.get("bio");
            int wins = Integer.parseInt(userRow.get("wins"));
            int losses = Integer.parseInt(userRow.get("losses"));
            ret.add(new User(userID, username, bio, wins, losses));
        }
        return ret;
    }

    public int countUsers(Database db, String wildcardMatch) throws SQLException {
        Object[] args = (excludeID == null)
                ? new Object[]{ wildcardMatch }
                : new Object[]{ wildcardMatch, excludeID };

        List<Map<String, String>> results = db.query(getCountUsersQuery(), args);
        String count = results.get(0).get("count");
        return Integer.parseInt(count);
    }

    private String getUsersQuery() {
        return "SELECT userID, nickname, bio, wins, losses " +
                "FROM users " +
                "WHERE nickname LIKE ? " +
                getExcludeIDCheck() +
                "LIMIT ?";
    }

    private String getCountUsersQuery() {
        return "SELECT COUNT(*) AS count " +
                "FROM users " +
                "WHERE nickname LIKE ? " +
                getExcludeIDCheck();
    }

    private String getExcludeIDCheck() {
        if (excludeID == null) {
            return "";
        }
        return "AND userID <> ? ";
    }

    /* The following methods exist only for testing purposes and are not used
       during normal execution, including the constructor. */

    public UsersRequest() {
        this.requestType = "users";
    }
}
