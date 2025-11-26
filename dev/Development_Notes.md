# Blog Project

Make a CRUD blog project.

## Idea

The idea is to use Spring Boot framework for the CRUD,
a python script for testing the REST APIs,
and docker for deployment.

Extended goals:
- PostgreSQL for database
- Angular frontend to access CRUD and manual testing


## Spring Boot setup

The first is the initial project setup with Spring Boot.

## Maven Commands

     ./mvnw package

      java -jar target/blog-0.0.1-SNAPSHOT.jar 

## Spring Security

Make the main end point public for now.

Disable the CSRF (Cross-Site Request Forgery) for now since the REST APIs
are meant to be stateless.

CORS (Cross-Origin Resource Sharing) should be added if there
is a web front end later.

TODO: Revist this when adding authentication

https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#_securityfilterchain_endpoints

## Docker for Spring Boot

Next setup the docker for the spring boot app

Created the docker file

Two stages, build and run

Build stage

    sudo docker build -t blog-app:latest .

Run stage

    sudo docker run -p 7075:7075 -t blog-app:latest


Some typical docker commands

    sudo docker ps

    sudo docker ps -a

    sudo docker images

    sudo docker rmi

Dockerfile for the Spring Boot app has been setup and working.

## Python script

Next is the python script for testing the rest endpoints.

Probably best to use pytest

https://laerciosantanna.medium.com/mastering-restful-api-testing-with-pytest-56d22460a9c4

Added a simple test for the test rest get method

Added custom logging for pytests

    pytest --log-cli-level=INFO rest_test.py



## Docker for python

Next up is setting up docker for the python tests

    sudo docker build -t pytest:latest .

    sudo docker run -t pytest:latest

## Docker compose

Ok now that Dockerfiles for both spring boot and pytest has been setup
its time to orchestrate and put them together with the docker compose.

    sudo docker compose build

    sudo docker compose up

    sudo docker compose up --build

Docker compose added, but the pytest is starting earlier than spring boot

Docker build for server has a health check, which can wait till server is on

Base url now set as an env var, so that python script test can use
different baseurl for development env and in production

Port mapped to different port to avoid conflict with development env

## Rest APIs

Now that the whole infrastructure has been setup its time to get
back to creating the actual rest apis.

Added the get all posts and the delete apis

Now adding the post create rest point api

For validation:

https://www.baeldung.com/spring-boot-bean-validation

All the rest apis added.

## Python script

Pytest for all the rest apis added.
The script needs polishing

The test script is now rewritten

Added error hanlding tests

https://laerciosantanna.medium.com/mastering-restful-api-testing-with-pytest-56d22460a9c4

## Align with schema

Fixed unnecessary wrapper for posts response which did not align with schema

## Unit tests

Now the last part is to add test for the Post Service

## OpenAPI swagger

Align as much as possible with given schema wrt api doc


