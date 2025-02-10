#!/bin/bash

# Define the working directory
WORKING_DIR="deployment/docker-compose"

echo "Stopping all services..."
#docker compose -f $WORKING_DIR/docker-compose.apps.yml down

#sleep 20

docker compose -f $WORKING_DIR/docker-compose.databases-infra.yml down

echo "All services have been stopped successfully!"