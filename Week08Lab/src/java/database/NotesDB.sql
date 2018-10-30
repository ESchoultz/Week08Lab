DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

CREATE TABLE users (
    username VARCHAR(20) NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    firstname VARCHAR(20), 
    lastname VARCHAR(20), 
    email VARCHAR(40), 
    CONSTRAINT PK_username PRIMARY KEY (username));

CREATE TABLE notes (
    noteid          int AUTO_INCREMENT NOT NULL,
    dateCreated     DateTime NOT NULL,
    contents        NVARCHAR(1000) NOT NULL,
    PRIMARY KEY (noteid));

INSERT INTO users(username, password)
VALUES("admin","password");
COMMIT;