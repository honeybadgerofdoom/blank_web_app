package com.tco.database;

import java.sql.*;

public class QueryBuilder {
    public static PreparedStatement countUsers() throws Exception {
        return createStatement("SELECT COUNT(*) AS count FROM users");
    }

    public static PreparedStatement getAllUsers() throws Exception {
        return createStatement("SELECT * FROM users");
    }

    public static PreparedStatement getMatches(String username) throws Exception {
        String sql = "SELECT gameID, userID, board, color, turn, nickname FROM games NATURAL JOIN userGames NATURAL JOIN users WHERE nickname=?";
        PreparedStatement statement = createStatement(sql);
        statement.setString(1, username);
        return statement;
    }

    public static PreparedStatement addUser(String nickname, String email) throws Exception {
        String sql = "INSERT INTO users (nickname, email, password, salt) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = createStatement(sql);
        statement.setString(1, nickname);
        statement.setString(2, email);
        statement.setString(3, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
        statement.setString(4, "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc");

        return statement;
    }

    private static PreparedStatement createStatement(String SQL) throws Exception {
        Connection conn = DriverManager.getConnection(
                Credential.getConnectionURL(),
                Credential.USER,
                Credential.PASSWORD
        );
        return conn.prepareStatement(SQL);
    }
}
