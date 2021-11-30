package com.tco.requests;

import com.tco.misc.UnauthorizedRequestException;
import com.tco.database.Database;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuitGameRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(QuitGameRequest.class);
    private int gameID;
    private int userID;
    private int secondPlayer;
    private int losses;
    private int wins;
    private boolean success;


    @Override
    public void buildResponse() {
            currentUserLost();
            secondPlayerWin();
            deleteGameFromDatabase();
        log.trace("buildResponse -> {}", this);
    }

    private void currentUserLost() {
        try (Database db = new Database()) {
            losses = getLosses(db, this.userID);
            losses++;
            String convertLoss = Integer.toString(losses);
            String query = "UPDATE users SET losses=? WHERE userID=?";
            db.update(query, convertLoss, this.userID); 
            this.success = true;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    }

   private void secondPlayerWin(){
        try (Database db1 = new Database()) {
            secondPlayer= getPlayerTwoID(this.gameID, this.userID); 
            wins = getWins(db1, secondPlayer);
            wins= wins + 1;
            String convertWins = Integer.toString(wins);
            String query = "UPDATE users SET wins=? WHERE userID=?";
            db1.update(query, convertWins, secondPlayer); 
            this.success = true;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    }

    private void deleteGameFromDatabase(){
        String query = "DELETE FROM games WHERE gameID=?";
        try (Database db = new Database()) {
            db.update(query, this.gameID);
            this.success = true;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    } 
    
    private int getPlayerTwoID(int gameID, int userID){
        int convertPlayer = 0;
        try (Database db1 = new Database()) {
            String query = "SELECT player1 FROM games WHERE gameID=?";
            List<Map<String, String>> secondPlayer = db1.query(query, gameID);
            convertPlayer = Integer.parseInt(secondPlayer.get(0).get("player1"));
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
        this.success = true;
        return convertPlayer;
    }
    
    private int getWins (Database db, int secondPlayer) throws Exception {
        String query = "SELECT wins FROM users WHERE userID=?";
        List<Map<String, String>> results = db.query(query, secondPlayer);
        return Integer.parseInt(results.get(0).get("wins"));
    }

    private int getLosses (Database db, int userID) throws Exception {s
        String query = "SELECT losses FROM users WHERE userID=?";
        List<Map<String, String>> results = db.query(query, userID);
        return Integer.parseInt(results.get(0).get("losses"));
    }



}
