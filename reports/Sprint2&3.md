# Sprint 2&3 - T02 - Team Reakt

## Review 

### Epics completed
<strong>Backend User Registration:</strong> For this epic, we added APIs for Login and Register on the server that allow a client to create an account and authenticate themselves on our webpage. This involved creating JSON schemas to validate the request and response, as well as adding the /api/register and /api/login endpoints. The general database implementation was also required to make this work. We set up a class for connecting to, querying, and updating our remote database.


<strong>Can Return to Game After Logout:</strong> This epic entails the user can return to a game after logging out. This functionality was achieved by how the state of the board is being stored in the database.


<strong>Front end user registration and login:</strong> For this epic, we added front end functionality to allow users to register and login. This involved adding tabs to contain the registration and login forms, as well as code to submit the relevant request to its API endpoint.


<strong>Playing Chess SERVER :</strong> For this epic, we implemented the functionality of playing chess on the server. This involved the specification of logic per piece as well as certain moves depending on specific criteria. We sent move requests from the client that would then reach the server and update the database board state as needed. 


<strong>Team About Page:</strong> For this epic, we added text and images to an “about us” tab so that users could, if they wanted to, learn more about the group that created the platform they’re using.


<strong>View the Rules:</strong> For this epic, we just included a general description of how the board works, and gave an explanation of rules for each individual piece.


<strong>Playing Chess CLIENT:</strong> This epic involved creating a chessboard on the website, and dynamically populating that board with the state saved in the database. We wrote a handful of API’s to extract the board state based on game id, allow a user to move pieces around, and store the new board state. We display possible moves on the board by coloring squares differently, depending on the return value of the legalMoves API.


## Retrospective

### Things that went well
As a team, the things we felt went well during these sprints were our communication, delegation of tasks, and the progress we made on the entire project. We all felt as though we had enough to work on at a given time and our division of labor in our sub-groups made us more productive.  


### Things that we need to improve
As a team, in the upcoming sprints we will improve our Code Climate, our completion of tasks in a timely manner, performance of regular housekeeping tasks for our repo, and our implementation to focus on the design patterns discussed in class. Our Code Climate began to drop significantly, so in the future we will regularly refactor  to improve code quality and remove duplication. When working on a particular task for a prolonged amount of time, we will seek assistance or ask a teammate to take on the task.

### One thing we will change next time
We will evaluate and either merge or close pull requests in a timely manner and delete the branch once it has been merged.

