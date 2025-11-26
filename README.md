# Blog Post REST API - Spring Boot

A simple REST API based on a Spring Boot server for managing blog posts.

## Quick Start

Step 1:

Prerequisites:

- Docker installed on your machine.
- Port 8080 available for server.

Step 2:

Command:

    sudo docker compose up --build

Step 3:

Grab a coffee as the application builds.

After the script-based tests pass, the server will still be
available on port 8080 if you wish to test it out manually.

## Tech Stack

- Spring Boot 3 - Spring Web simplifies development of REST API based server
  and Spring Data JPA will make it straight forward to integrate with a database
- Python Script - For testing the REST endpoints with pytest
- Docker - Multi stage build for different components with docker compose orchastration
- Maven - For building the Spring Boot server
- JUnit 5 - For java unit testing

## Project Structure

root/
|-server
|-script


## Future Improvements

- Replace `ConcurrentHashMap` with real backend database.
- Angular based front-end for demonstration of managing blog posts.
  
