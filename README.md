🚎 User service 🚎
============================================================================================================================

## Service to manage the users (CRUD).

Provides the APIs and the create, read, update and delete users.

## Tech stack

- Java 8
- Spring boot 2.1.1
- Swagger
- H2 in memory database
- Maven
- Docker

## Compile and run

With the maven wrapper:

1) `./mvnw clean verify`
2) `./mvnw spring-boot:run`

With docker

1) `docker build -t user-service .`
2) `docker run -p 8080:8080 user-service`

## URLs

Running locally, use the Swagger URL

- Swagger

http://localhost:8080/swagger-ui.html
