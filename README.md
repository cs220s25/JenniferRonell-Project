
# Overview
## Project Description

This project is a Discord bot that is modeled after the popular board game Battleship. The bot will allow a single
 user to play against it in a game of Battleship. The bot is responsible for placing three ships varying in length
 on a 7x7 grid and allowing the user to guess the location of the ships using a coordinate on the grid. The bot will
 respond with a hit or miss message and will keep track of the user's past moves and ships sunk.

<img src="./UML.png" alt="UML Diagram" width="700"/>

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
* Copy the credentials.

```bash
[default]
aws_access_key_id=ASIAVBIXLOLWZGSECRET
aws_secret_access_key=gyud18iuUSACCESSKEY
aws_session_token=IQoJb3JpZ2luX2VjECsaCXVzLXdlc3QtMiJHMEUCIFNJRpsBQBxwT+nRg1vX7xAFN7zSmvU/OvW9kbS9M1lFAiEAt3PQREALLY_LONG_TOKEN
```
3. Paste the credentials into ~/.aws/credentials.

4. Start Redis in a new terminal window.
```bash
redis-server
```

5. In a seperate, new terminal window, clone the repository.
```bash
git clone https://github.com/cs220s25/JenniferRonell-Project.git
```

6. Navigate to the project.
```bash
cd ~/JenniferRonell-Project
```

7. Package the project.
```bash
mvn package
```

8. Run the bot.
```bash
java -jar target/dbot-1.0-SNAPSHOT.jar
```

## Deploy with Docker

<b>1. Launch an EC2 Instance

* Use the same LabInstanceProfile and Vockey key pair as in the AWS section.

* Under Advanced → User Data, paste the contents of docker_userdata.sh:
```bash
#!/bin/bash

#Install and configure Docker
yum install -y docker
systemctl enable docker
systemctl start docker

# Add ec2-user to the docker group so that it can run docker commands without sudo.
usermod -a -G docker ec2-user

yum install -y git
yum install -y maven

git clone https://github.com/cs220s25/JenniferRonell-Project.git /home/ec2-user/JenniferRonell-Project

cd /home/ec2-user/JenniferRonell-Project

#Make sure all of the files are executable
chmod +x *.sh

mvn clean package

./docker_up.sh

```

<b>2. Connct to the EC2 instance via SSh

```bash
 ssh -i ~/.ssh/labsuser.pem ec2-user@<Your IP address>

```

<b>3. Prepare the Environment

* Once everything is packed and completed, run the `./docker_up.sh`

* If you are still having trouble with running this, try running `docker build -t discord-bot .`



<b>4. Build and Deploy Containers

* Use the provided docker_up.sh script to build and launch the Docker containers.

``` bash
./docker_up.sh
```
* This script performs the following tasks:

* Creates a Docker network named dbot (if it doesn’t already exist).
* Removes any existing containers named redis or discord-bot-container.
* Builds a new Docker image from the Dockerfile and tags it as discord-bot.
* Launches a Redis container and connects it to the dbot network.
* Starts the Discord bot container with the required environment variables:
* REDIS_HOST=redis
* REDIS_PORT=6379 

<b>5. Monitor the Bot
* You can view logs using:

``` bash
docker logs -f discord-bot-container
```
This will show the bot starting up and any activity it's performing (e.g., responding to Discord commands).


<b> 6. Shut Down the Docker Stack
* To safely stop the bot and Redis, use the docker_down.sh script:

```bash
./docker_down.sh
```

* This script will:

* Stop and remove the Discord bot container

* Save Redis data to disk (via redis-cli save)

* Stop and remove the Redis container

* Remove the Docker network dbot if it exists



## Deploy with AWS

<b>1. Create a Discord Bot Token</b>
* Go to the Discord Developer Portal.
* Click "New Application", give it a name, and create it.
* Under the "Bot" section, create a bot for your application.
* Copy the bot's token (you will need it for the next step).


<b>2. Store the Token in AWS Secrets Manager</b>
* Open the AWS Console, and go to Secrets Manager.
* Click Store a new secret.
* Select Other type of secret.

 In the Key/Value section:

* Key: DISCORD_TOKEN
* Value: (paste your Discord bot token here)
* Click Next.
* On the "Configure Secret" page:
* Secret Name: 220_Discord_Token
* Click through the remaining screens keeping the default values and then click "Store".

<b>3. Launch an EC2 Instance in AWS</b>
* Key Pair: Vockey
* Under Advanced → IAm Instance Profile, choose LabInstanceProfile.
* Under Advanced → User Data, paste the contents of userdata.sh below.
```bash
#!/bin/bash
yum install maven-amazon-corretto21 -y
yum install git -y
yum install redis6 -y

git clone https://github.com/cs220s25/JenniferRonell-Project /home/ec2-user/JenniferRonell-Project

cd /home/ec2-user/JenniferRonell-Project
mvn clean package
cp discord_bot.service /etc/systemd/system/
systemctl start discord_bot.service
redis6-server
```
Once the EC2 Instance is created and running, the bot will be active and replying to its designated commands!

# Redeploying

<b>1. Create GitHub Action Secret: Public IP</b>
* Retrieve the Public IPv4 address from your AWS EC2 Instance.
* Navigate to JenniferRonell-Project Repository → Settings → Secrets and Variables → Actions.
* Click on "New repository secret".
* Name: PUBLICIP
* Secret: Paste the public IP address.

<b>2. Create GitHub Action Secret: Labsuser key</b>
* Launch AWS learner lab.
* Under AWS Details, click Show next to "SSH key".
* Copy the entire RSA private key, including the begin and end
```bash
-----BEGIN RSA PRIVATE KEY-----
       <RSA_PRIVATE_KEY>
-----END RSA PRIVATE KEY-------
``` 
* Navigate to the GitHub Repository.
* Go to Settings → Secrets and Variables → Actions.
* Click on "New repository secret".
* Name: LABSUSERPEM
* Secret: Paste the RSA private key.

<b>3. Run Deploy on AWS</b>
* After making changes to the source code, push your changes.
* Navigate to the JenniferRonell-Project repository → Actions.
* Under "All workflows", select "Run on AWS".
* Click "Run workflow", then "Run workflow".

After completing these steps, your updated code will automatically redeploy, and the changes will take effect in your Discord bot.

# Technologies Used
* GitHub
* Maven
* Redis
* Discord
* Docker
* Amazon Web Services (AWS)

# Background

[How to Use the Redis Docker Official Image](https://www.docker.com/blog/how-to-use-the-redis-docker-official-image/)

[Deploying with GitHub Actions](https://docs.github.com/en/actions/use-cases-and-examples/deploying/deploying-with-github-actions)

[Create an AWS Secrets Manager secret](https://docs.aws.amazon.com/secretsmanager/latest/userguide/create_secret.html)

**Parts of the setup instructions, CI/CD pipeline guidance, and project documentation were developed with the assistance of OpenAI's ChatGPT*

