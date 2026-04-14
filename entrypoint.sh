#!/bin/sh
# Extract PORT from environment or use default
PORT=${PORT:-8081}

# Run Spring Boot with the PORT argument
java -jar app.jar --server.port=$PORT
