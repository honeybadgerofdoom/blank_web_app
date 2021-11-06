package com.tco.requests.Users;

public class User {
    private final int userID;
    private final String username;
    private final String bio;

    public User(int userID, String username, String bio) {
        this.userID = userID;
        this.username = username;
        this.bio = (bio != null) ? bio : "";
    }
}
