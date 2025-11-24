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



