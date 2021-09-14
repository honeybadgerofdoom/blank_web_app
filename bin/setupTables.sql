--
-- Initial setup:
--

-- Create database (if needed)
CREATE DATABASE IF NOT EXISTS chess;

-- Connect to our db
CONNECT chess;

-- Recreate tables every time this script is run
DROP TABLE IF EXISTS users,
                     userProfile,
                     profiles;


--
-- Tables:
--

CREATE TABLE users (
    userID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,

    --These fields are set as fixed width, with the assumption they will hold a SHA256 hash
    password CHAR(64) NOT NULL,
    salt CHAR(64) NOT NULL,

    PRIMARY KEY (userID) 
);

CREATE TABLE profiles (
    profileID INT NOT NULL AUTO_INCREMENT,
    bio VARCHAR(255),
    picURL VARCHAR(255),
    PRIMARY KEY (profileID)
);

-- 
-- Relations:
--

CREATE TABLE userProfile (
    userID INT NOT NULL,
    profileID INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users (userID),
    FOREIGN KEY (profileID) REFERENCES profiles (profileID),
    UNIQUE (userID, profile)
);
