INSERT INTO `users` VALUES
(101, 'april', 'wckelly@rams.colostate.edu', null, null, 0, 0, 'E+LF8Kllm862WsaVOaB0ohb36ZYRowP3sDjgprX0cF8=', 'b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc'),
(102, 'darin', 'darinh@rams.colostate.edu', null, null, 0, 0, 'E+LF8Kllm862WsaVOaB0ohb36ZYRowP3sDjgprX0cF8=', 'b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc'),
(103, 'matt', 'asterix@rams.colostate.edu', null, null, 0, 0, 'E+LF8Kllm862WsaVOaB0ohb36ZYRowP3sDjgprX0cF8=', 'b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc'),
(104, 'kassidy', 'kbarram@rams.colostate.edu', null, null, 0, 0, 'E+LF8Kllm862WsaVOaB0ohb36ZYRowP3sDjgprX0cF8=', 'b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc'),
(105, 'aaron', 'catninja@rams.colostate.edu', null, null, 0, 0, 'E+LF8Kllm862WsaVOaB0ohb36ZYRowP3sDjgprX0cF8=', 'b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc'),
(106, 'lani', 'lani09br@rams.colostate.edu', null, null, 0, 0, 'E+LF8Kllm862WsaVOaB0ohb36ZYRowP3sDjgprX0cF8=', 'b61ec97750d754b09cd822317d0f5c3a7aae75bd1c4bbbf2cf08afa77123b2dc');

INSERT INTO `games` VALUES
(201, 101, 102, 'BLACK', 'rnbqkb-r-pppp-ppp----p-----n------P----------P--PP-PP-PPRNBQKBNR'),
(202, 101, 103, 'WHITE', 'rnbqkbnrpppppppp----------------------P-------P-PPPPPP-PRNBQKBNR'),
(203, 103, 104, 'BLACK', 'rnbqkbnr--------pppppppp--------PPPPPPPP----------------RNBQKBNR'),
(204, 105, 106, 'WHITE', 'r-bqkbnrpppppppp--n------------------------P----PPP-PPPPRNBQKBNR'),
(205, 105, null, 'WHITE', 'rnbqkbnrpppppppp--------------------------------PPPPPPPPRNBQKBNR');

INSERT INTO `invites` VALUES
(205, 105, 101, 'PENDING'),
(205, 105, 103, 'PENDING');