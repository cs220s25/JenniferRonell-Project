#!/bin/bash
yum install git -y
git clone https://github.com/cs220s25/JenniferRonell-Project /JenniferRonell-Project
cd /JenniferRonell-Project
python3 -m venv .venv
.venv/bin/pip install -r requirements.txt 
cp discord_bot.service /etc/systemd/system
systemctl enable discord_bot.service 
systemctl start discord_bot.service
