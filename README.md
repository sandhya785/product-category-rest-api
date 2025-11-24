# Product-Category REST API

## Overview
This is a Spring Boot REST API application for managing products and categories.  
It provides secure, efficient, and scalable APIs for CRUD operations on products and categories, along with features like JWT authentication, validation, pagination, sorting, and Swagger documentation.

This project demonstrates backend development skills using modern Java and Spring Boot technologies.

## Features
- CRUD operations for Products and Categories via REST endpoints
- JWT-based authentication and authorization
- Input validation using `@Valid`
- Global exception handling for better error responses
- Pagination and sorting for API responses
- Swagger UI documentation for testing APIs
- Unit tests for controllers and services using JUnit and Mockito

## Technologies Used
- **Java 21**
- **Spring Boot 3.5.6**
- Spring Data JPA & Hibernate
- **MySQL**
- Maven
- JWT for authentication
- Swagger for API documentation
- JUnit & Mockito for unit testing

## Tools & Utilities
- **Postman** – for API testing
- **jwt.io** – for verifying JWT tokens
- **Swagger UI** – interactive API documentation

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/product-category-rest-api.git
## API Endpoints
- **Category** - /api/categories
- **Product** - /api/products
- **User** - /user
-  **Supports GET, POST, PUT, DELETE operations with pagination and sorting.**