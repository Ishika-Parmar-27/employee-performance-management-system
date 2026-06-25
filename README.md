# Employee Performance Management System

A **Spring Boot-based backend system** designed to manage employees, departments, projects, and performance reviews with secure role-based access, file handling, notifications, and production-ready features.

---

## Features

### Architecture
- Spring Boot Layered Architecture (Controller → Service → Repository)
- Clean separation of concerns

### Core Modules
- Employee Module
- Department Module
- Project Module
- Performance Module

---

## Security
- JWT Authentication
- Role-Based Access Control (RBAC)
  - HR
  - Manager
  - Employee
- Spring Security integration

---

## Database & ORM
- Hibernate (JPA)
- Entity Relationships:
  - One-to-One
  - One-to-Many
  - Many-to-Many

---

## Key Functionalities

- Employee Profile Management
- Department Management
- Project Assignment System
- Performance Review System
- File Upload (Employee Profile Photo)
- Email Notifications (Performance Review Alerts)
- Global Exception Handling
- Logging using SLF4J

---

## API Features

- Pagination
- Sorting
- Search API (Employees, Projects, etc.)
- RESTful API design

---

## Documentation
- Swagger API Documentation for all endpoints

---

## Testing
- Unit Testing using JUnit
- Service & Controller layer test coverage

---

##  DevOps & Deployment

- Dockerized Spring Boot Application
- Docker support for easy deployment
- Deployable on Render / cloud platforms
- Ready for CI/CD integration

---

##  Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL / PostgreSQL
- Maven
- Docker
- Swagger / OpenAPI

---

## Project Setup

### 1. Clone Repository
```bash
git clone https://github.com/your-username/employee-performance-system.git
cd employee-performance-system
