INSERT INTO `users` VALUES
(101, "wckelly", "wckelly@rams.colostate.edu", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(102, "darinh", "darinh@rams.colostate.edu", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(103, "asterix", "asterix@rams.colostate.edu", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(104, "kbarram", "kbarram@rams.colostate.edu", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(105, "catninja", "catninja@rams.colostate.edu", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc"),
(106, "lani09br", "lani09br@rams.colostate.edu", null, null, "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc");

INSERT INTO `games` VALUES
(201, 101, 102, 'BLACK', 'rnbqkb-r-pppp-ppp----p-----n------P----------P--PP-PP-PPRNBQKBNR'),
(202, 101, 103, 'WHITE', 'rnbqkbnrpppppppp----------------------P-------P-PPPPPP-PRNBQKBNR'),
(203, 103, 104, 'BLACK', 'rnbqkbnr--------pppppppp--------PPPPPPPP----------------RNBQKBNR'),
(204, 105, 106, 'WHITE', 'r-bqkbnrpppppppp--n------------------------P----PPP-PPPPRNBQKBNR'),
(205, 105, null, 'WHITE', 'rnbqkbnrpppppppp--------------------------------PPPPPPPPRNBQKBNR');

INSERT INTO `invites` VALUES
(205, 105, 101, 'PENDING'),
(205, 105, 103, 'PENDING');