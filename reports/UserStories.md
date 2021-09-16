### Playing the game
#### Description
As a user, I can play the game of chess according to the rules.
#### Conversation
The game exists on the website, as some kind of virtual chessboard that users can interact with. The game knows which moves are legal and which are illegal, and automatically enforces the rules of chess so that it is impossible for users to make an illegal move. The application knows whose turn it is and does not allow users to make moves out of turn. The application knows when the game is over, and who won. 
#### Tests
- Test that when a user moves a piece the board updates
- Test that a user can't move out of turn
- Test that a user can't make an illegal move
- Test that the system recognizes when the game is over
- Test that the system knows who won a completed game

### Playing multiple games at once
#### Description
As a user, I can play multiple games at once.
#### Conversation
Several games can be played at a time, and those games are unrelated. The games may begin and end at any time, unaffected by the status or participants of any other game.
#### Tests
- Test that a user be involved in multiple games at once
- Test that a user's moves in one game does not affect their activity in another game
- Test that if a user quits one game, they do not leave any other game
- Test that joining a new game does not affect existing games

### Register/Unregister account
#### Description
As a user, I can register and unregister an account.
#### Conversation
Completing the registration form on the website creates an account for a user, and that account and its information are saved. Registered users are able to log in to their account. Choosing to delete the account causes the account and all of its information to be removed from the datase. Deleted accounts cannot be logged in to.
#### Tests
- Test that completing the registration form adds an entry to the Users table in the database
- Test that logging in to a registered account succeeds
- Test that the information belonging to a user persists between logging out and logging back in
- Test that deleting an account removes that entry from the database
- Test that trying to log in to a deleted account fails
- Test that creating a new account with the same name/username/email as a deleted account yields a new, blank account
