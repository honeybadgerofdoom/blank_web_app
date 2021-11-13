package com.tco.requests;

import java.util.HashMap;
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

public class NewGameRequest extends Request {
    
    private final transient Logger log = LoggerFactory.getLogger(BoardRequest.class);
    private int userID;

    private String[] boardString;
    private boolean success;
    
    @Override
    public void buildResponse() {
        createGame();
        log.trace("buildResponse -> {}", this);
    }

    private void createGame() {
        try (Database db = new Database()) {
            db.update(createGameQuery(), this.userID);
            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
    }

    private static String createGameQuery() {
        return "INSERT INTO `games` VALUES (" +
                "null," +
                "?," +
                "null," +
                "'WHITE'," +
                "'rnbqkbnrpppppppp--------------------------------PPPPPPPPRNBQKBNR')";
    }

}
