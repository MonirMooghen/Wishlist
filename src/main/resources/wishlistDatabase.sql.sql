DROP DATABASE IF EXISTS wishlistDatabase;
CREATE DATABASE wishlistDatabase;

USE wishlistDatabase;

DROP TABLE IF EXISTS Profile;
CREATE TABLE Profile (
	profileId	INT AUTO_INCREMENT,
    profileName	TEXT,
    profileLastName TEXT,
    profileEmail TEXT,
    profilePassword TEXT,
	PRIMARY KEY(profileId)
);

DROP TABLE IF EXISTS Wishlist;
CREATE TABLE Wishlist (
	wishlistId	INT AUTO_INCREMENT,
	wishlistName	TEXT,
    profileId INT,
	PRIMARY KEY(wishlistId),
    FOREIGN KEY(profileId) REFERENCES Profile(profileId)
);

DROP TABLE IF EXISTS Wish;
CREATE TABLE Wish (
	wishId	INT AUTO_INCREMENT,
	wishName	TEXT,
    wishDescription TEXT,
    wishPrice INT,
    wishLink TEXT,
    wishlistId INT,
	PRIMARY KEY(wishId),
    FOREIGN KEY(wishlistId) REFERENCES Wishlist(wishlistId)
);

