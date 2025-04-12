#!/bin/bash
yum install -y maven-amazon-corretto21
git clone https://github.com/cs220s25/JenniferRonell-Project /JenniferRonell-Project
cd /JenniferRonell-Project
mvn clean package
cp discord_bot.service /etc/systemd/system/
systemctl enable discord_bot.service
systemctl start discord_bot.service
