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
