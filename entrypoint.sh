#!/bin/sh
# Extract PORT from environment or use default
PORT=${PORT:-8081}

# Run Spring Boot - let Spring read environment variables directly
# Spring will resolve properties from environment variables automatically
java -jar app.jar \
  --server.port=$PORT
