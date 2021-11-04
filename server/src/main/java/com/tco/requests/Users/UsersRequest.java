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
    private int limit;
    private int found;
    private List<User> users;

    @Override
    public void buildResponse() {
        String wildcardMatch = "%" + match + "%";
        try (Database db = new Database()) {
            this.users = getUsers(db, wildcardMatch, limit);
            this.found = countUsers(db, wildcardMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.trace("buildResponse -> {}", this);
    }

    public List<User> getUsers(Database db, String match, int limit) throws SQLException {
        if (limit == 0)
            limit = DEFAULT_LIMIT;

        List<Map<String, String>> results = db.query(getUsersQuery(), match, limit);

        List<User> ret = new ArrayList<>();
        for (Map<String, String> userRow : results) {
            int userID = Integer.parseInt(userRow.get("userID"));
            String username = userRow.get("nickname");
            String bio = userRow.get("bio");
            ret.add(new User(userID, username, bio));
        }
        return ret;
    }

    public int countUsers(Database db, String match) throws SQLException {
        List<Map<String, String>> results = db.query(getCountUsersQuery(), match);
        String count = results.get(0).get("count");
        return Integer.parseInt(count);
    }

    private String getUsersQuery() {
        return "SELECT userID, nickname, bio " +
                "FROM users " +
                "WHERE nickname LIKE ? " +
                "LIMIT ?";
    }

    private String getCountUsersQuery() {
        return "SELECT COUNT(*) AS count " +
                "FROM users " +
                "WHERE nickname LIKE ?";
    }

    /* The following methods exist only for testing purposes and are not used
       during normal execution, including the constructor. */

    public UsersRequest() {
        this.requestType = "users";
    }
}
