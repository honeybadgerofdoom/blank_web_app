--
-- Initial setup:
--

-- Create database (if needed)
CREATE DATABASE IF NOT EXISTS chess;

-- Connect to our db
USE chess;

-- Recreate tables every time this script is run
DROP TABLE IF EXISTS
    users,
    games,
    notifications;


--
-- Tables:
--

CREATE TABLE users (
    userID INT NOT NULL AUTO_INCREMENT,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    bio VARCHAR(1024),
    picture BLOB,

    -- These fields are set as fixed width, with the assumption they will hold a SHA256 hash
    password CHAR(64) NOT NULL,
    salt CHAR(64) NOT NULL,

    PRIMARY KEY (userID)
);

CREATE TABLE games (
    gameID INT NOT NULL AUTO_INCREMENT,
    player1 INT NOT NULL,
    player2 INT,
    turn ENUM ('WHITE', 'BLACK') NOT NULL DEFAULT 'WHITE',
    board CHAR(64) NOT NULL DEFAULT 'rnbqkbnrpppppppp--------------------------------PPPPPPPPRNBQKBNR',
    FOREIGN KEY (player1) REFERENCES users (userID),
    FOREIGN KEY (player2) REFERENCES users (userID),
    PRIMARY KEY (gameID)
);

CREATE TABLE notifications (
    gameID INT NOT NULL,
    sender INT NOT NULL,
    receiver INT NOT NULL,
    status ENUM ('PENDING', 'ACCEPTED', 'CANCELLED'),
    type ENUM ('INVITE', 'GAME_WON'),

    FOREIGN KEY (gameID) REFERENCES games (gameID),
    FOREIGN KEY (sender) REFERENCES users (userID),
    FOREIGN KEY (receiver) REFERENCES users (userID),

    PRIMARY KEY (gameID, sender, receiver)
);

-- 
-- Relations:
--
/* CREATE TABLE userGames (
    gameID INT NOT NULL,
    userID INT NOT NULL,
    color enum ('WHITE', 'BLACK') NOT NULL,
    FOREIGN KEY (userID) REFERENCES users (userID),
    FOREIGN KEY (gameID) REFERENCES games (gameID),
    UNIQUE (gameID, color)
); */

