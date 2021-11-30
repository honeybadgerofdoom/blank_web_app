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
            String query = "UPDATE users SET losses=losses+1 WHERE userID=?";
            db.update(query, this.userID); 
            this.success = true;
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
    }

   private void secondPlayerWin(){
        try (Database db1 = new Database()) {
            secondPlayer= getSecondPlayerID(this.gameID, this.userID); 
            String query = "UPDATE users SET wins=wins+1 WHERE userID=?";
            db1.update(query, secondPlayer); 
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
    
    private int getSecondPlayerID(int gameID, int userID){
        int convertPlayer = 0;
        try (Database db1 = new Database()) {
            String query = "SELECT player1 FROM games WHERE gameID=?";
            List<Map<String, String>> secondPlayer = db1.query(query, gameID);
            convertPlayer = Integer.parseInt(secondPlayer.get(0).get("player1"));
            if(convertPlayer != userID){
                this.success = true;
                return convertPlayer;
            }
            String query = "SELECT player2 FROM games WHERE gameID=?";
            List<Map<String, String>> secondPlayer = db1.query(query, gameID);
            convertPlayer = Integer.parseInt(secondPlayer.get(0).get("player2"));
        } catch (Exception e) {
            this.success = false;
            e.printStackTrace();
        }
        this.success = true;
        return convertPlayer;
    }
}
