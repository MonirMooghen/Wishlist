USE wishlistDatabase;

INSERT INTO Profile (profileName, profileLastName, profileEmail, profilePassword) VALUES ('Monir', 'Mooghen', 'momo0003@stud.kea.dk', '12341234A?');
INSERT INTO Profile (profileName, profileLastName, profileEmail, profilePassword) VALUES ('Josefine', 'Røes', 'joro0003@stud.kea.dk', '12341234B;');
INSERT INTO Profile (profileName, profileLastName, profileEmail, profilePassword) VALUES ('Tobias', 'Barbosa', 'toha0006@stud.kea.dk', '12341234C=');
INSERT INTO Profile (profileName, profileLastName, profileEmail, profilePassword) VALUES ('Vahab', 'Lotfyyekta', 'valo0001@stud.kea.dk', '12341234D_');

INSERT INTO Wishlist (wishlistName) VALUES ('Jul');
INSERT INTO Wishlist (wishlistName) VALUES ('Fødselsdag');
INSERT INTO Wishlist (wishlistName) VALUES ('Byllup');
INSERT INTO Wishlist (wishlistName) VALUES ('Barnedåb');

INSERT INTO Wish (wishName, wishDescription, wishPrice, wishLink, wishlistId) VALUES ('cykel', 'en flot rød cykel', '4000', 'rødcykel.dk', 1);
INSERT INTO Wish (wishName, wishDescription, wishPrice, wishLink, wishlistId) VALUES ('blå bluse', 'en flot blå bluse', '200', 'blåbluse.dk', 2);
INSERT INTO Wish (wishName, wishDescription, wishPrice, wishLink, wishlistId) VALUES ('øreringe', 'flotte guldøreringe', '10000', 'guldøreringe.dk', 3);
INSERT INTO Wish (wishName, wishDescription, wishPrice, wishLink, wishlistId) VALUES ('mobilcover', 'et slidt sort mobilcover', '10', 'mobilcover.dk', 4);

SELECT * FROM Wishlist; 
SELECT * FROM Wish;