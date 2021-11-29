package com.tco.requests.Users;

public class User {
    private final int userID;
    private final String username;
    private final String bio;
    private final int wins;
    private final int losses;

    public User(int userID, String username, String bio, int wins, int losses) {
        this.userID = userID;
        this.username = username;
        this.wins = wins;
        this.losses = losses;
        this.bio = (bio != null) ? bio : "";
    }
}
