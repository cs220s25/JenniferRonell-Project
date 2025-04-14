#!/bin/bash
yum install maven-amazon-corretto21 -y
yum install git -y
yum install redis6 -y

git clone https://github.com/cs220s25/JenniferRonell-Project.git
cd /JenniferRonell-Project
mvn clean package
python3 -m venv .venv
cp discord_bot.service /etc/systemd/system/
systemctl enable discord_bot.service
systemctl start discord_bot.service
redis6-server
