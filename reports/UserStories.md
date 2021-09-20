# User Stories
### Playing the game
#### Description
As a user, I can play the game of chess according to the rules.
#### Conversation
The game exists on the website, as some kind of virtual chessboard that users can interact with. The game knows which moves are legal and which are illegal, and automatically enforces the rules of chess so that it is impossible for users to make an illegal move. The application knows whose turn it is and does not allow users to make moves out of turn. The application knows when the game is over, and who won.
#### Acceptance Criteria
- Test that when a user moves a piece the board updates
- Test that a user can't move out of turn
- Test that a user can't make an illegal move
- Test that the system recognizes when the game is over
- Test that the system knows who won a completed game

<hr/>

### Playing multiple games at once
#### Description
As a user, I can play multiple games at once.
#### Conversation
Several games can be played at a time, and those games are unrelated. The games may begin and end at any time, unaffected by the status or participants of any other game.
#### Acceptance Criteria
- Test that a user be involved in multiple games at once
- Test that a user's moves in one game does not affect their activity in another game
- Test that if a user quits one game, they do not leave any other game
- Test that joining a new game does not affect existing games
- Test that a user can view a list of all matches they are apart of

<hr/>

### Register/Unregister account
#### Description
As a user, I can register and unregister an account.
#### Conversation
Completing the registration form on the website creates an account for a user, and that account and its information are saved. Registered users are able to log in to their account. Choosing to delete the account causes the account and all of its information to be removed from the datase. Deleted accounts cannot be logged into.
#### Acceptance Criteria
- Test that completing the registration form adds an entry to the Users table in the database
- Test that logging in to a registered account succeeds
- Test that the information belonging to a user persists between logging out and logging back in
- Test that deleting an account removes that entry from the database
- Test that trying to log in to a deleted account fails
- Test that creating a new account with the same name/username/email as a deleted account yields a new, blank account

<hr/>

### Match Invitations
#### Description
As a user, I can send an invite for other registered users to join and play a match.

#### Conversation
A player can find an opponent to invite to a game by means of searching or viewing a table of registered users. The player will then be able to send an invitation to their selected opponent and the invitee will then be prompted to either accept or decline the invitation. The inviter will then be notified of the invitee’s answer. When an invitation is accepted, a match will be created between the two players.

#### Acceptance Criteria
- Test if user can send invitations to other users
- Test that a user gets a notification when they receive an invitation
- Test that a user can accept an invitation they receive
- Test that a user can decline an invitation they receive
- Test that a user gets notified when another user accepts the invitation
- Test that a user gets notified when another user declines the invitation
- Test that accepting makes a match
- Test that a match is only created with the first person who accepts the invitation

<hr/>

### Viewing User Profiles
#### Description
As a user, I can view my own profile or those of the other registered users.

#### Conversation
A player can select another user's profile to view their game statistics and profile information. This includes a user picture and a short bio section.

#### Acceptance Criteria
- Test if the user can view other user’s profiles
- Test if the user can view their own profile
- Test if the user can see their statistics update on their profile
- Test if the user can see other user’s statistics update on their profile
- Test to see if the database is updating with all information 

<hr/>

### Quit the game
#### Description
As a user, I can quit the game at any time.
#### Conversation
The platform will have an option to quit the game at any time while the game is being played. Once a user decides to quit their current game, a second window will appear confirming whether or not the user wants to quit the game or not. If they select yes, the game ends for both users. If they select no, the game resumes for both users. If the user selects yes, the game will be stored in each user profile as an abandon game.
#### Tests
- Test that when a user quits the game, they can not revist the game
- Test that when a user quits the game, it does not quit any other game they are currently playing, for both players
- Test that when a user quits the game, it is stored in the database as an abandon game for both players
- Test that when a user quits the game, is is added to their profile, for both players

<hr/>

### View the Rules
#### Description
As a user, I can view the rules at any time.
#### Conversation
A player can easily nagivate to the rules at any time whether before, during, or after a game.  The rules are very straight forward, easy to understand, and easy to read. A player will be able to read the rules, top to bottom, or have the option to search for specific words and be taken to that specific part of the rules.
#### Tests
- Test that a user can navigate to the rules while playing the game
- Test that the rules are legible
- Test that the search bar takes you to the specified rule
- Test if the profile is only visible to registered users

<hr/>

### Searching for Users
#### Description
As a user, I can see a list of all registered users or search for a specific user to view or play against.

#### Conversation
A user can search and filter through all other registered users to find an opponent. The query results may be narrowed down using a partial or exact username. A form of sorting might be permitted to make users easier to find.

#### Acceptance Criteria
- Test that entering a partial word filters the list of users
- Test that searching for an exact name returns one result, since names are unique
- Test that all registered users are listed if there is no search term
- Test that the results list can be sorted alphabetically or by recent activity

<hr/>

### Updating User Profiles
#### Description
As a user, I can update and personalize my profile.

#### Conversation
A registered user can visit their own profile and configure it to their liking. Information such as nickname, avatar, and bio can be edited and saved for others to see.

#### Acceptance Criteria
- Test that a user can only update their own profile information
- Test that changes persist across page reloads and logouts
- Test that the user can change the nickname
- Test that the new nickname must be unique
- Test that the user can change the pic/ avatar
- Test that the user can change the bio
- Test updates with long text inputs
- Test updates with empty text inputs
- Test that other users can see the changes (the database is updated)

<hr/>

### Navigating the page
#### Description
As a user, I can navigate the web page between the different sections based on whether I want to play a game, view a users profile, or register/unregister.

#### Conversation
The user profiles, registration page, and game board is all on a website. Different tabs would allow one to switch between the different features.

#### Test
-Test that there are multiple tabs at the top of the web page
-Test that the tabs take you to the correct pages.

<hr/>

### Recording game history and stats
#### Description
As a user, I can have my statistics related to games I've played recorded.

#### Conversation
When you finish a game, information related to that game will be stored and displayed on their profile. This information includes the times and dates the game began and ended, who won and who lost the game, and whether the game was abandoned partway through. All this information will be recorded for multiple games, and will all be displayed on a users profile. 

#### Tests
-Test that the start time and date are recorded when a game is started.
-Test that the end date and time are recorded when a game is finished.
-Test that the way the game ends is recorded (who wins and loses, or whether the game was abandoned)
-Test that the game information that is recorded updates on the users profile correctly.
-Test that the information for multiple games can be recorded at the same time.

