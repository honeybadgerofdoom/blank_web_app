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

### Match Invitations 
#### Description
As a user, I can send an invite for other registered users to join and play a match.

#### Conversation
A player can find an opponent to invite to a game by means of searching or viewing a table of registered users. The player will then be able to send an invitation to their selected opponent and the invitee will then be prompted to either accept or decline the invitation. The inviter will then be notified of the invitee’s answer. When an invitation is accepted, a match will be created between the two players. 

#### Tests
- Test if user can send invitations to other users
- Test that a user gets a notification when they receive an invitation
- Test that a user can accept an invitation they receive
- Test that a user can decline an invitation they receive
- Test that a user gets notified when another user accepts the invitation
- Test that a user gets notified when another user declines the invitation
- Test that accepting makes a match

### Viewing User Profiles
#### Description
As a user, I can view my own profile or those of the other registered users. 

#### Conversation
A player will be able to view profiles by searching for a specific user or by choosing a player from a list of registered users. Once a user’s profile is selected, their game statistics and profile information will be displayed. The profile information will contain customizable features such as a user picture and a short bio section. If the player is viewing their own profile, they will be able to edit their own profile information. 

#### Tests
- Test if the user can view other user’s profiles
- Test if the user can view their own profile
- Test if the user can change the pic
- Test if the user can change the bio
- Test if the user can see their statistics update on their profile
- Test if the user can see other user’s statistics update on their profile
- Test to see if the database is updating with all information 

### Quit the game
#### Description
As a user, I can quit the game at any time.
#### Conversation
The game will have an option to quit the game at any time. Once a user decides to quit the game, a second window will appear confirming whether or not the user wants to quit the game. If they select yes, the game ends for both users. If they select no, the game resumes for both users. If the user selects yes, the game will be stored in each user profile as an abandon game.
#### Tests
- Test that when a user quits the game, they can not revist the game
- Test that when a user quits the game, it does not quit any other game they are currently playing, for both players
- Test that when a user quits the game, it is stored in the database as an abandon game for both players
- Test that when a user quits the game, is is added to their profile, for both players

### View the Rules
#### Description
As a user, I can view the rules at any time.
#### Conversation
A player can easily nagivate to the rules at any time whether before, during, or after a game.  The rules are very straight forward, easy to understand, and easy to read. A player will be able to read the rules, top to bottom, or have the option to search for specific words and be taken to that specific part of the rules.
#### Tests
- Test that a user can navigate to the rules while playing the game
- Test that the rules are legible
- Test that the search bar takes you to the specified rule
