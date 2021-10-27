package com.tco.requests.LoginRegister;

import com.tco.database.Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class PasswordUtil {
    public static String randomSalt() {
        return "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc";
    }

    public static String fetchSalt(Database db, String username) throws SQLException {
        List<Map<String, String>> results;
        results = db.query(getSaltQuery(), username, username);

        if (results.isEmpty()) {
            return null;
        }
        return results.get(0).get("salt");
    }

    public static String sha256(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getSaltQuery() {
        return "SELECT salt " +
                "FROM users " +
                "WHERE nickname = ? OR email = ?";
    }
}
