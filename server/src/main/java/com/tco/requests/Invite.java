package com.tco.requests;

public class Invite {
    private int userID;
    private String sender;
    private String receiver;
    private String status;

    public Invite(int userID, String sernder, String receiver, String status) {
        this.userID = userID;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

}