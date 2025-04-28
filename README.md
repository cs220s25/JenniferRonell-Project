
# Overview
## Project Description

This project is a Discord bot that is modeled after the popular board game Battleship. The bot will allow a single
 user to play against it in a game of Battleship. The bot is responsible for placing three ships varying in length
 on a 7x7 grid and allowing the user to guess the location of the ships using a coordinate on the grid. The bot will
 respond with a hit or miss message and will keep track of the user's past moves and ships sunk.

## Usage

The user can interact with the bot by sending messages in the Discord chat.
The following commands are viable for the bot to respond to:<br>
`!playbattleship` - Starts the game of battleship. <br>
`!endgame` - Ends a current game of battleship. <br>
`!status` -  Displays the current status of the game (Moves remaining and ships left to sink). <br>
`!help`  -  Displays a list of commands that the bot will respond to. <br>


# Setup

## Local Deployment


1. Create an AWS credentials folder
```bash
mkdir ~/.aws
```
2. Get your AWS credentials:
	* Start the AWS Learner Lab.
	* Click AWS Details → Show under AWS CLI.
	* Copy the credentials:

```bash
[default]
aws_access_key_id=ASIAVBIXLOLWZGSECRET
aws_secret_access_key=gyud18iuUSACCESSKEY
aws_session_token=IQoJb3JpZ2luX2VjECsaCXVzLXdlc3QtMiJHMEUCIFNJRpsBQBxwT+nRg1vX7xAFN7zSmvU/OvW9kbS9M1lFAiEAt3PQREALLY_LONG_TOKEN
```
3. Paste the credentials into ~/.aws/credentials.

4. Start Redis in a new terminal window
```bash
redis-server
```

5. Clone the repository
```bash
git clone https://github.com/cs220s25/JenniferRonell-Project.git
```

6. Navigate to the project (Using a new terminal window seperate from redis)
```bash
cd ~/JenniferRonell-Project
```

7. Package the project
```bash
mvn package
```

8. Run the bot
```bash
java -jar target/dbot-1.0-SNAPSHOT.jar
```

## Deploy with Docker


## Deploy with AWS