# JWT Authentication System

A secure and scalable JWT Authentication & Authorization system built using Spring Boot and Spring Security.

---

# Features

* User Registration & Login
* JWT Token Generation & Validation
* Spring Security Integration
* Role Based Access Control (RBAC)
* BCrypt Password Encryption
* Stateless Authentication
* Custom JWT Filter
* Secure API Endpoints
* PostgreSQL Database Integration
* RESTful API Architecture

---

# Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* PostgreSQL
* Hibernate / JPA
* Gradle
* Lombok

---

# Authentication Flow

1. User registers an account
2. Password is encrypted using BCrypt
3. User logs in with username and password
4. JWT token is generated after successful authentication
5. Token is sent in Authorization Header
6. JWT Filter validates token for every request
7. Spring Security authorizes access based on roles

---

# RBAC (Role Based Access Control)

## Roles Supported

* USER
* ADMIN

---

## Access Rules

| Endpoint     | Access      |
| ------------ | ----------- |
| `/public/**` | Public      |
| `/user/**`   | USER, ADMIN |
| `/admin/**`  | ADMIN Only  |

---

# RBAC Flow Diagram

```text
Client Request
      │
      ▼
Authorization Header
(Bearer Token)
      │
      ▼
JWT Filter
      │
      ▼
Validate JWT Token
      │
      ▼
Extract Username
      │
      ▼
Load User From Database
      │
      ▼
Get Authorities / Roles
      │
      ▼
ROLE_USER / ROLE_ADMIN
      │
      ▼
Spring Security
Authorization Manager
      │
      ▼
hasRole() / hasAnyRole()
      │
      ├───────────────┐
      │               │
      ▼               ▼
Access Granted    403 Forbidden
```

---

# JWT Structure

JWT contains:

* Subject (Username)
* Role
* Issued Time
* Expiration Time
* Digital Signature

---

# API Endpoints

## Authentication APIs

### Register User

```http
POST /public/signup
```

### Login User

```http
POST /public/login
```

---

## User APIs

```http
GET /user/**
```

Accessible By:

* USER
* ADMIN

---

## Admin APIs

```http
GET /admin/**
```

Accessible By:

* ADMIN Only

---

# Security Features

* Stateless Session Management
* BCrypt Password Hashing
* JWT Signature Verification
* Role-Based Authorization
* Secure REST APIs
* Custom JWT Authentication Filter

---

# Project Structure

```bash
src/main/java
│
├── Controller
├── DTO
├── Entity
├── Repository
├── Security
├── Service
└── Config
```

---

# Getting Started

## Clone Repository

```bash
git clone https://github.com/chhotukumar09/jwt-authentication-system.git
```

---

## Configure Database

Update `application.properties`

```properties
spring.datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
```

---

## Run Project

```bash
./gradlew bootRun
```

---

# Example Authorization Header

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# Example Roles

```json
{
  "role":"ADMIN"
}
```

```json
{
  "role":"USER"
}
```

---

# Future Improvements

* Refresh Token Implementation
* Email Verification
* Password Reset
* OAuth2 Login
* Docker Support
* API Documentation using Swagger

---

# Author Chhotu kumar

Chhotu Kumar

---

# Repository

https://github.com/chhotukumar09/jwt-authentication-system
