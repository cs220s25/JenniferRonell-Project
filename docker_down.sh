#!/bin/bash

# Stop and remove the Discord bot container if it exists
docker rm -f discord-bot-container 2>/dev/null || true

# Save Redis data to disk before stopping
if docker ps -q -f name=redis >/dev/null; then
    echo "Saving Redis data..."
    docker exec -it redis redis-cli save
fi

# Stop and remove the Redis container
docker rm -f redis 2>/dev/null || true

# remove the dbot network
if docker network inspect dbot &>/dev/null; then
    docker network rm dbot
    echo "Network 'dbot' removed."
else
    echo "Network 'dbot' does not exist."
fi

