#!/usr/bin/env bash
docker build -t auth0-samples/auth0-java-ee-01-login .
docker run -p 3000:8080 -it auth0-samples/auth0-java-ee-01-login
