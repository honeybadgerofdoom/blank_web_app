package com.tco.database;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDatabase {
    @Test
    @DisplayName("Test database connection")
    public void testDatabaseConnection() throws Exception {
        Database db = new Database();
        List<Map<String, String>> rows = db.queryDB(QueryBuilder.countUsers());
        int numUsers = Integer.parseInt(rows.get(0).get("count"));
        assertTrue(numUsers > 0);
    }
}
