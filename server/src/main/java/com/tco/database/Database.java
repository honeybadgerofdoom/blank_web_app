package com.tco.database;

import java.sql.*;
import java.util.*;

public class Database {

    private static Connection dbc;

    public Database() {
        connect();
    }

    private void connect() {
        try {
            this.dbc = DriverManager.getConnection(
                    Credential.getConnectionURL(),
                    Credential.USER,
                    Credential.PASSWORD
            );
        } catch (SQLException e) {
            System.err.println("Error connecting to database");
            e.printStackTrace();
        }
    }

    private static PreparedStatement prepare(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = dbc.prepareStatement(query);
        return bindParms(statement, parameters);
    }

    public static ResultSet query(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = prepare(query, parameters);
        return statement.executeQuery();
    }


    public static List<Map<String, String>> queryDB(PreparedStatement statement) throws Exception {
        try (statement; ResultSet results = statement.executeQuery()) {
            List<Map<String, String>> ret = new ArrayList<>();
            ResultSetMetaData rsMetaData = results.getMetaData();

            while (results.next()) {
                ret.add(new HashMap<>());
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    String columnName = rsMetaData.getColumnLabel(i);
                    String columnValue = results.getString(columnName);

                    ret.get(ret.size()-1).put(columnName, columnValue);
                }
            }
            return ret;
        }
    }

    public static int updateDB(PreparedStatement statement) throws Exception {
        try (statement) {
            return statement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            List<Map<String, String>> rows = queryDB(QueryBuilder.countUsers());
            System.out.println("Total users: " + rows.get(0).get("count"));

            System.out.println("\nUsers:\n");
            rows = queryDB(QueryBuilder.getAllUsers());
            for (Map<String, String> row : rows) {
                for (String key : row.keySet()) {
                    System.out.println(key + ": " + row.get(key));
                }
                System.out.println();
            }

            String username = "april";
            System.out.printf("\nMatches for username '%s':\n\n", username);
            rows = queryDB(QueryBuilder.getMatches(username));
            for (int i = 0; i < rows.size(); i++) {
                Map<String, String> row = rows.get(i);
                System.out.printf("Match %d (gameID=%s)\n", i+1, row.get("gameID"));
                System.out.printf("  User #%s: %s - color=%s\n", row.get("userID"), row.get("nickname"), row.get("color"));
                System.out.printf("  Board: %s\n", row.get("board"));
                boolean isTurn = row.get("color").equals(row.get("turn"));
                System.out.printf("  %s's turn? %s\n", username, isTurn);

                System.out.println();
            }

            int rowsUpdated = updateDB(QueryBuilder.addUser("aaron", "catninja@rams.colostate.edu"));
            if (rowsUpdated == 1) {
                System.out.println("User successfully added.");
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
