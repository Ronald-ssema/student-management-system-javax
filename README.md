# Student Management System (Java / Spring Boot)

A backend API for managing students, departments, courses, programs, and enrollments.

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA + Hibernate
- H2 (in-memory) for development
- Maven

## Setup
```bash
./mvnw spring-boot:run
# Then in another terminal:
curl -X POST http://localhost:8080/api/enrollments -H "Content-Type: application/json" -d '{"studentId":1,"offeringId":1}'

