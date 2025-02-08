#!/bin/bash

# Define the working directory
WORKING_DIR="deployment/docker-compose"

echo "Stopping all services..."
docker compose -f $WORKING_DIR/docker-compose.yml down

echo "All services have been stopped successfully!"