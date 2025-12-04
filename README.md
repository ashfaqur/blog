# Blog Post REST API - Spring Boot

A simple Spring Boot–based REST API for managing blog posts.

Easy server setup using Docker along with a Python-based test client.

## Architecture

![Architecture](/assets/BlogPostCurrent.png)

## Quick Start

### Prerequisites:

- Docker installed.
- Port 8080 available for server.

### Run the project

    sudo docker compose up --build

After the Docker containers start, the Python script will run automatically to test the APIs.

Afterwards the server will still be available on port 8080.

### Swagger UI
 
UI for interactively trying out the REST API

http://localhost:8080/swagger-ui/index.html

## Tech Stack

- Spring Boot 3 - REST API development using Spring Web
- Python + Pytest - For testing the APIs
- Docker - Multi-stage build for the API server and test client
- Maven - For building the Spring Boot server

## Project Structure

    root/
    - docker-compose.yml
    |-server - Java Spring Boot server
    |-script - Python script client for testing


## Future Improvements - Add a Database

The current implementation uses an in-memory `ConcurrentHashMap`.

This was chosen for demo simplicity but is not suitable for production because:
- Data is in memory and not persistent.
- Memory limit can cause out of memory exception.
- Multi-step updates are not atomic and can cause issues with multi-threading.

## Future Work - A Front-End

A Angular front-end would be a great addition with blog posts and UI controls for
managing them. An intuitive way for a user to seamlessly play around with the blog
posts while inherently testing the endpoints.

For now, Swagger UI is there for testing the REST APIs.

## Authentication

JWT based authentication for the stateless REST APIs  
