SELECT 'Loading users' as 'INFO';

INSERT INTO `users` VALUES
(101, "wckelly@ram.colostate.edu", "april", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(102, "darinh@rams.colostate.edu", "darinh", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(103, "matt@example.com", "matt", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc");

INSERT INTO `games` VALUES
(301, 'BLACK', 'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn');

INSERT INTO `userGames` VALUES
(301, 101, 'WHITE'),
(301, 102, 'BLACK');
