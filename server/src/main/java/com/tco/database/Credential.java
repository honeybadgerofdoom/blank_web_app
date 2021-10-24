package com.tco.database;

public class Credential {
    final static String USER = "darinh";
    final static String PASSWORD = "831915869";

    static String getConnectionURL() {
        String useTunnel = System.getenv("T02_USE_DATABASE_TUNNEL");
        if (useTunnel != null && useTunnel.equals("true")) {
            return "jdbc:mariadb://127.0.0.1:56247/cs414_team2";
        }
        return "jdbc:mariadb://faure.cs.colostate.edu:3306/cs414_team2";
    }
}