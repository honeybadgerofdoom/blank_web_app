# Sprint 4 - T02 - Team Reakt

## Review 

### Epics completed
<strong>Searching for Users:</strong> In this epic we wrote an API to search the database for all users, or for all users matching a search string. We then used this API on the client for the profile page and match invitations.


<strong>Match Invitations:</strong> This epic involved providing users with the ability to invite other registered players to play a match. We created server APIs to update the database with new invitations and games, and hooked up the client components to send requests when necessary. A modal was created to be able to filter and select users through checkboxes, and the players receiving the invites are then able to accept or deny the request.


<strong>Viewing User Profiles:</strong> In this epic we built out a user’s profile page. This included a section to view and update the user’s email address and bio, a section to view their stats, and a section to search for/view other users. The wins and losses are shown for the logged in user and any other user you search for.


<strong>User Experience:</strong> This epic contained many small changes to our webpage.  We created a rules page using cards.  We also added scalable images for each chess piece both on the rules section and on the chess board as a visual enhancement.  We created an about page, also using cards, that gives descriptions of each team member. Polling was added to the Play page to get back the latest Board and Game state from the database after a short interval. 

<strong>Quitting the Game:</strong> We wrote a server API that allows users to forfeit a game. The player who quit then gets another loss on their record, and the other player gets a win. This is persistent and managed in the database.


## Retrospective

### Things that went well
Our scrum meetings were very productive and attendance we good. We managed to keep our code fairly clean, which kept our codebase maintainable. We did a good job of distributing tasks and staying on top of completion times. We covered the majority of our Client's requirements and have delivered a beautiful, function application.


### Things that we need to improve
We could have done a better job communicating about when we expect to finish tasks, so that team members with dependant tasks are not left waiting. We should have put a larger emphasis on writing tests - our client and server side test coverage is fairly low. We should have held eachother accountable to the 1-week max turnaround time for tasks.

### One thing we will change next time
Next time we will do a better job of keeping our test coverage up, and enforcing Test Driven Development.
