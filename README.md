# 🛒 Product & Category REST API

A complete Spring Boot backend providing CRUD operations for **Products** and **Categories**, with JWT authentication, input validation, global exception handling, pagination, sorting, and Swagger documentation.

---

## 🚀 Features
- **CRUD operations** for Products and Categories
- **JWT Authentication & Authorization**
- **Input Validation** using `@Valid`
- **Global Exception Handling**
- **Pagination & Sorting** support
- **Swagger UI** for interactive API testing
- **Unit Tests** using JUnit & Mockito
- **Deployed on Render** using **PostgreSQL (Free Tier)**

---

## 🧰 Technologies Used
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA & Hibernate
- PostgreSQL
- Maven
- JWT (JSON Web Token)
- Swagger / OpenAPI
- JUnit 5 & Mockito

---

## 🛠 Tools & Utilities
- **Postman** – API testing
- **Swagger UI** – API documentation
- **jwt.io** – JWT verification

---

## 🌐 Live Deployment (Render)

**Base URL:**

https://product-category-rest-api.onrender.com

**Swagger UI:**

https://product-category-rest-api.onrender.com/swagger-ui/index.html

---

## 📌 API Endpoints

### **Category APIs** (`/api/categories`)
- `GET /api/categories`
- `POST /api/categories`
- `PUT /api/categories/{id}`
- `DELETE /api/categories/{id}`

Supports:
- Pagination → `?page=0&size=5`
- Sorting → `?sort=name,asc`

---

### **Product APIs** (`/api/products`)
- `GET /api/products`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

Supports:
- Pagination
- Sorting
- Category linking

---

### **User APIs** (`/user`)
- `POST /user/register`
- `POST /user/login` → returns JWT token

### Add token in header:

- Authorization: Bearer <token>

---

## 🧪 Unit Tests
- Controller tests
- Service tests  
  Using **JUnit 5** and **Mockito**.

### Run tests:

- mvn test

---

## 📝 Setup Instructions (Local)

### 1️⃣ Clone the repository
```bash
git clone https://github.com/<your-username>/product-category-rest-api.git

2️⃣ Configure application.properties (PostgreSQL)

spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

3️⃣ Run the application

mvn spring-boot:run


---

👩‍💻 Author

Sandhya
Java Backend Developer | Spring Boot | REST APIs

---
