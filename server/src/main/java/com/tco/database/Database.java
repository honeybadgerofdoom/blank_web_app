package com.tco.database;

import java.sql.*;
import java.util.*;

public class Database implements AutoCloseable{

    private Connection dbc;

    public Database() {
        connect();
    }

    private void connect() {
        try {
            dbc = DriverManager.getConnection(
                    Credential.getConnectionURL(),
                    Credential.USER,
                    Credential.PASSWORD
            );
        } catch (SQLException e) {
            System.err.println("Error connecting to database");
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        this.dbc.close();
    }

    private void bindParam(PreparedStatement s, int index, Object parameter) throws SQLException {
        int parameterNum = index + 1;

        if (parameter instanceof String) {
            s.setString(parameterNum, (String) parameter);
        } else if (parameter instanceof Integer) {
            s.setInt(parameterNum, (Integer) parameter);
        } else if (parameter instanceof Double) {
            s.setDouble(parameterNum, (Double) parameter);
        } else if (parameter instanceof Float) {
            s.setFloat(parameterNum, (Float) parameter);
        }

    }

    private void bindParams(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            bindParam(statement, i, parameters[i]);
        }
    }

    private PreparedStatement prepare(String query) throws SQLException {
        return dbc.prepareStatement(query);
    }

    public List<Map<String, String>> query(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = prepare(query)) {
            bindParams(statement, parameters);
            ResultSet results = statement.executeQuery();
            return parseResults(results);
        }
    }

    private List<Map<String, String>> parseResults(ResultSet results) throws SQLException {
        try (results) {
            List<Map<String, String>> ret = new ArrayList<>();
            ResultSetMetaData rsMetaData = results.getMetaData();

            while (results.next()) {
                ret.add(new HashMap<>());
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    String columnName = rsMetaData.getColumnLabel(i);
                    String columnValue = results.getString(columnName);

                    ret.get(ret.size() - 1).put(columnName, columnValue);
                }
            }
            return ret;
        }
    }

    public int update(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = prepare(query)) {
            bindParams(statement, parameters);
            return statement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try (Database db = new Database()) {
            List<Map<String, String>> rows = db.query("SELECT COUNT(*) AS count FROM users");
            System.out.println("Total users: " + rows.get(0).get("count"));

            System.out.println("\nUsers:\n");
            rows = db.query("SELECT * FROM users");
            for (Map<String, String> row : rows) {
                for (String key : row.keySet()) {
                    System.out.println(key + ": " + row.get(key));
                }
                System.out.println();
            }

            int userID = 101;
            System.out.printf("\nMatches for user #%d:\n\n", userID);
            String sql = "SELECT gameID, player1, player2, turn, board FROM games WHERE player1=? OR player2=?";
            rows = db.query(sql, userID, userID);
            for (int i = 0; i < rows.size(); i++) {
                Map<String, String> row = rows.get(i);
                System.out.printf("Match %d (gameID=%s)\n", i+1, row.get("gameID"));
                System.out.printf("  Player 1: user %s - WHITE\n", row.get("player1"));
                System.out.printf("  Player 2: user %s - BLACK\n", row.get("player2"));
                System.out.printf("  Board: %s\n", row.get("board"));
                System.out.printf("  Turn: %s\n", row.get("turn"));

                System.out.println();
            }

            int rowsUpdated = db.update(
                    "INSERT INTO users (nickname, email, password, salt) VALUES (?, ?, ?, ?)",
                    "aaron",
                    "catninja@rams.colostate.edu",
                    "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                    "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc" );
            if (rowsUpdated == 1) {
                System.out.println("User successfully added.");
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
