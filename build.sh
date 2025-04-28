#!/bin/bash

# Build the Java project using Maven
mvn clean package

# Build the Docker image
docker build -t discord-bot .
