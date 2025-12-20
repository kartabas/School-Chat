#!/bin/bash

# Deployment script for Spring Boot application
# This script stops containers, pulls latest code, builds, and redeploys

set -e  # Exit on any error

# Start timer
START_TIME=$(date +%s)

# Change to project directory
cd ~/app/School-Chat

echo "=================================="
echo "Starting deployment process..."
echo "Working directory: $(pwd)"
echo "=================================="

# Step 1: Stop running containers
echo ""
echo "[1/5] Stopping Docker containers..."
docker-compose down
echo "✓ Containers stopped"

# Step 2: Pull latest code from git
echo ""
echo "[2/5] Pulling latest code from git..."
git pull
echo "✓ Code updated"

# Step 3: Build application with Maven
echo ""
echo "[3/5] Building application with Maven..."
mvn clean package -DskipTests
echo "✓ Maven build completed"

# Step 4: Build Docker image
echo ""
echo "[4/5] Building Docker image..."
docker build -t springboot-app .
echo "✓ Docker image built"

# Step 5: Start containers
echo ""
echo "[5/5] Starting Docker containers..."
docker-compose up -d
echo "✓ Containers started"

echo ""
echo "=================================="
echo "Deployment completed successfully!"
echo "=================================="

# Calculate execution time
END_TIME=$(date +%s)
DURATION=$((END_TIME - START_TIME))
MINUTES=$((DURATION / 60))
SECONDS=$((DURATION % 60))

echo ""
echo "Total execution time: ${MINUTES} minutes ${SECONDS} seconds"

# Show running containers
echo ""
echo "Running containers:"
docker-compose ps
