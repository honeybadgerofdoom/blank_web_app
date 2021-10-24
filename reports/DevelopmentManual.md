<h1 align="center">
  Extinction Chess Web App
  <br>
</h1>

<h4 align="center">A fully-featured and user-friendly <a href="https://en.wikipedia.org/wiki/Extinction_chess" target="_blank">Extinction Chess</a> implementation using a ReactJS client and Java Spark microserver.</h4>

<br>


<p align="center">
  <a href="#development-environment">Development Environment</a> •
  <a href="#getting-the-code">Getting the Code</a> •
  <a href="#navigating-the-project">Navigating the Project</a> •
  <a href="#building-and-running-the-web-app">Building and Running</a> •
  <a href="#testing">Testing</a>
</p>

# Development and Usage Instructions
Interested in contributing to the development of our application? Or just want to try it out? Here are the steps you can follow to set up your environment, build the code, run the tests, and begin working on the project.

## Development Environment
For the most reliable and error-free experience, we recommend that you run our project only on a **Unix** system. This leaves 3 primary options that you can use for your development environment:

1. **Local Linux or Mac:** Use VSCode or IntelliJ IDEA and run the code in your local environment normally. See the database writeup for how to set up port forwarding if you are not on the CSU network.
2. **Lab machines or VSCode RemoteSSH:** Visit the lab machines in-person or install the `Remote - SSH` extension to access them remotely. The project will run without additional setup in this environment. 
3. **VSCode with Docker:** Install the VSCode `Remote - Containers` extension and pull our image `csucs314/developer-image`. Open the container in the editor using the explorer panel.

Once you select an environment, see our [Database Manual](https://github.com/CSU-CS-414-Fall-2021/t02/tree/main/reports/database) that details how to access our remote database running on Faure at CSU.

Note: All of these choices require that you use **NPM <= 6** and **Node <= 14** to be compatible with our package versions. Additionally, Java versions higher than 11 may fail to compile.

## Getting the Code

### SSH Keys

GitHub requires developers to generate and upload SSH keys to your account for security reasons. This will allow you to clone and make changes to the repository. To do so, you can use a command like this:

`ssh-keygen -t rsa -b 4096`

Then upload the entire file-contents of **~/.ssh/id_rsa.pub** (or whatever you named it) to the SSH Keys section of your GitHub account settings.

### Git Config

Next, you should configure your local Git settings to help identify yourself in your commits.

```bash
git config --global user.name 'Firstname Lastname'
git config --global user.email you@domain.com
```

Check your work with `git config --list`.

### Pulling the Repository

You are now ready to clone the repository to begin development.

```bash
# Clone this repository
$ git clone git@github.com:CSU-CS-414-Fall-2021/t02.git

# Move to the project directory
$ cd t02
```

You might choose to open the t02 folder directly in your editor.

## Navigating the Project

The repository is divided into two primary folders: client and server. 
The **client** folder holds all of the source code and tests for the user interface built with ReactJS. On the other hand, the **server** folder has Java code and tests pertaining to the database, game logic, and microserver. Finally, the run script is in the **bin** directory and the compiled files are built to the **target** directory.

```bash
t02
├── bin
│   └── run.sh
├── client
│   ├── package.json
│   ├── schemas
│   ├── src
│   ├── templates
│   └── test
├── server
│   ├── pom.xml
│   └── src
└── target
```

## Building and Running the Web App

Running the code is made very simple by our run script. Simply open a terminal in the t02 directory and use this command:

`./bin/run.sh`

This will download the NPM and Maven dependencies, run the client and server tests, package the client files with Webpack, and compile the Java code for the server logic. Additionally, it begins hosting the webpage on port 3000 and the server on port 8000. When it finishes, the site will popup in the browser and should be ready for use!

You will find that making client changes is very easy since the **hotloader** will automatically update the page when it detects that a file has changed. Sadly, this does not apply to the Java code on the server, where you will need to re-run run.sh whenever you change the code. 

Note: port 3000 is usually automatically forwarded, but make sure port 8000 also gets forwarded if using VSCode (see the tower icon at the bottom). 

## Testing

A major part of test driven development is the ability to easily and quickly run your tests to check if your new implementations are working as expected and that they did not break another part of the system. Running "./bin/run.sh" will automatically do this for you. If you prefer to run the tests in a more efficient manner, you can do the following steps:

### Server tests

```bash
# Move to the server directory
cd server

# Run the Java tests
mvn test
```

### Client tests

```bash
# Move to the client directory.
cd client

# Run the JavaScript tests with Jest.
npm test

# Or from the t02 directory
npm test --prefix client

# Alternatively, select specific tests to run.
# This will run any tests that have app (case insensitive) in the file name.
npm test app
```
