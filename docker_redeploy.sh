#!/bin/bash

# Configure Git safe directory
git config --global --add safe.directory /JenniferRonell-Project

# Navigate to project directory and pull latest changes
cd /JenniferRonell-Project
git pull origin main

# Build Docker image
docker build -t discord-bot .

# Remove existing container if it exists
docker rm -f discord-bot-container 2>/dev/null || true

# Run new container with Redis configuration
docker run -d --name redis -p 6379:6379 redis

# Start new Discord bot container, setting Redis environment variables
docker run -d --name discord-bot-container -e REDIS_HOST=localhost -e REDIS_PORT=6379 discord-bot

