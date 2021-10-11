--
-- Initial setup:
--

-- Create database (if needed)
CREATE DATABASE IF NOT EXISTS chess;

-- Connect to our db
CONNECT chess;

-- Recreate tables every time this script is run
DROP TABLE IF EXISTS userGames,
                     users,
                     games;


--
-- Tables:
--

CREATE TABLE users (
    userID INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    bio VARCHAR(1024),
    picURL VARCHAR(255),

    -- These fields are set as fixed width, with the assumption they will hold a SHA256 hash
    password CHAR(64) NOT NULL,
    salt CHAR(64) NOT NULL,

    PRIMARY KEY (userID) 
);

CREATE TABLE games (
                       gameID INT NOT NULL,
                       turn enum ('WHITE', 'BLACK') NOT NULL,
                       board CHAR(64) NOT NULL,
                       PRIMARY KEY (gameID)
);

-- 
-- Relations:
--
CREATE TABLE userGames (
    gameID INT NOT NULL,
    userID INT NOT NULL,
     color enum ('WHITE', 'BLACK') NOT NULL,
     FOREIGN KEY (userID) REFERENCES users (userID),
     FOREIGN KEY (gameID) REFERENCES games (gameID),
     UNIQUE (gameID, color)
);

