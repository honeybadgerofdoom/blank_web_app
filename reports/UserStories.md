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

### Returning to a game after logging out
#### Description
As a user, I can return to a game after I log out.

#### Conversation
What else?  Oh right.  The state of the matches should be saved in some way, so the user can play whenever she wants.  My guess is that users won't be playing the whole time, so for example, a user would make a move whenever is her turn and log out, and after a while she would come back and check if the other player made a move and it’s her turn again. 

#### Acceptance Criteria
- Test that a user can login.
- Test that a user can logout.
- Test that a game can be saved.
- Test that the relationship between a specific saved game and a specific user is preserved after log out.
- Test that a game can be loaded from a save.
- Test that a user can return to a game after logging out.

<hr/>

### Notification of an opponent’s move

#### Description
As a user, I can be notified when my opponents make a move.

#### Conversation
Asynchronous matches, I think that describes it.The system needs to know when a game is over **and should let know the players who won or lost**

#### Acceptance Criteria
- Test that a user can recieve a notification.
- Test that the system can send a notification.
- Test that a win triggers a notification.
- Test that a loss/concession triggers a notification.
- Test that both players are notified.
