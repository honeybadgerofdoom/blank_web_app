package com.tco.requests;

import com.tco.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class StatsRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(StatsRequest.class);
    private int userID;

    private int wins;
    private int losses;

    @Override
    public void buildResponse() throws Exception {
        getWinsLosses();
        log.trace("buildResponse -> {}", this);
    }

    private void getWinsLosses() {
        String query = "SELECT wins, losses FROM users WHERE userID=?";
        try (Database db = new Database()) {
            List<Map<String, String>> results = db.query(query, this.userID);
            this.wins = Integer.parseInt(results.get(0).get("wins"));
            this.losses = Integer.parseInt(results.get(0).get("losses"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
