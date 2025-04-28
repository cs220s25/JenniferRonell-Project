#!/bin/bash

# Navigate to project directory
cd /home/ec2-user/JenniferRonell-Project

# Create Docker network if it doesn't exist
if ! docker network inspect dbot &>/dev/null; then
    docker network create dbot
    echo "Network 'dbot' created."
else
    echo "Network 'dbot' already exists."
fi

# Remove existing containers if they exist
docker rm -f redis 2>/dev/null || true
docker rm -f discord-bot-container 2>/dev/null || true

# Start Redis container
docker run -d --network dbot --name redis -p 6379:6379 redis

# Start Discord Bot container with environment variables
docker run -d --network dbot --name discord-bot-container -e REDIS_HOST=redis -e REDIS_PORT=6379 discord-bot

