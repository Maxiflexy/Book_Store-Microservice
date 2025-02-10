#!/bin/bash


# Define the working directory
WORKING_DIR="deployment/docker-compose"

set -e  # Exit immediately if a command exits with a non-zero status

echo "🚀 Starting database containers..."
docker compose -f $WORKING_DIR/docker-compose.databases-infra.yml up -d

echo "🛠 Checking database health..."
# Loop until both catalog-db and orders-db are healthy
while [[ "$(docker inspect --format='{{json .State.Health.Status}}' catalog-db 2>/dev/null)" != "\"healthy\"" || \
         "$(docker inspect --format='{{json .State.Health.Status}}' orders-db 2>/dev/null)" != "\"healthy\"" ]]; do
  echo "⏳ Waiting for databases to become healthy..."
  sleep 5
done

echo "✅ Databases are healthy! Starting application services..."
#docker-compose -f docker-compose.app.yml up -d

echo "🎉 All services started successfully!"
