#!/bin/bash


# Define the working directory
WORKING_DIR="deployment/docker-compose"

# Start the infrastructure services (PostgreSQL)
echo "Starting infrastructure services..."
docker compose -f $WORKING_DIR/docker-compose.yml up -d

## Wait for PostgreSQL to be ready
#echo "Waiting for PostgreSQL to become healthy..."
#until docker inspect --format='{{.State.Health.Status}}' catalog-db | grep -q "healthy"; do
#  sleep 5
#  echo "Waiting..."
#done
#
#echo "PostgreSQL is ready!"
#
## Start the application services (catalog-service)
#echo "Starting application services..."
#docker compose -f $WORKING_DIR/apps.yml up -d

echo "All services started successfully!"