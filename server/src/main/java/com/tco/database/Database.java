package com.tco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Database {

    private static String database_path = "jdbc:sqlite:database.db";

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database_path);
        } catch (SQLException e) {
            System.out.println("SQLException caught in getConnection(): \'" + e.getMessage() + "\' - db will be null.");
        }
        return connection;
    }

    // Example code for creating a new database
    public static void createNewDatabase() {

        try (Connection connection = DriverManager.getConnection(database_path)) {
            if (connection != null) {
                DatabaseMetaData metadata = connection.getMetaData();
                System.out.println("The driver name is " + metadata.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Example code for verifying a hashed password
    public static void checkHashing() {
        String query = "SELECT password FROM users WHERE id=1";
        try (Connection db = getConnection();
             Statement statement = db.createStatement();
             ResultSet result = statement.executeQuery(query)){

            String stored_hash = result.getString("password");
            String plain_password = "password";
            if(BCrypt.checkpw(plain_password, stored_hash)) {
                System.out.println("They match. Huzzah.");
            }
            else {
                System.out.println("They don't match.");
            }

        } catch (Exception e) {
            System.out.println("Caught Exception in Database.java: \'" + e.getMessage() + "\'");
        }
    }

    // Example code for adding a Users table
    public static void addUserTable() {
        String createUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                + "		id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "		name STRING NOT NULL,\n"
                + "		type STRING NOT NULL,\n"
                + "		email STRING NOT NULL UNIQUE,\n"
                + "		password STRING NOT NULL\n"
                + ")";
        try (Connection db = getConnection();
             Statement statement = db.createStatement()) {
            statement.execute(createUsers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Example code for adding users to the table
    public static void addUser(String name, String type, String email, String password) {
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
        String query = "INSERT INTO users (name, type, email, password) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection db = getConnection();
             PreparedStatement prepared_statement = db.prepareStatement(query)) {
            prepared_statement.setString(1, name);
            prepared_statement.setString(2, type);
            prepared_statement.setString(3, email);
            prepared_statement.setString(4, hashed_password);
            prepared_statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
