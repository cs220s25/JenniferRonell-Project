#!/bin/bash

#Install and configure Docker
yum install -y docker
systemctl enable docker
systemctl start docker

# Add ec2-user to the docker group so that it can run docker commands without sudo.
usermod -a -G docker ec2-user


yum install -y git

git clone https://github.com/cs220s25/JenniferRonell-Project.git

cd /JenniferRonell-Project

#Make sure all of the files are executable
chmod +x *.sh

./build.sh

./docker_redeploy.sh
