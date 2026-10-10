# Smart Sales Store

A backend application for managing sales operations, designed with a focus on **clean architecture, domain-driven design, modularity, and scalable backend development**.

The project is being developed as a practical study of modern **Java and Spring Boot** backend engineering, with the architecture prepared to evolve into a distributed system.

---

## 🚀 Overview

**Smart Sales Store** is a sales management system built with Java 21 and Spring Boot.

The project follows a modular approach, where each business capability can be isolated into an independent service as the system evolves.

The current implementation starts with the **User Service**, responsible for user-related operations and persistence.

The project is designed to explore concepts commonly used in production backend systems:

* Domain-driven design
* Hexagonal / Clean Architecture
* RESTful APIs
* Microservices
* Relational database persistence
* Separation of domain and infrastructure concerns
* Automated testing
* Scalable project organization

---

## 🏗️ Architecture

The repository uses a **Gradle multi-module structure**, allowing new services to be added independently as the system grows.

```text
store-automation/
├── api-gateway
├── service-auth
├── service-users
├── service-customers
├── service-products
├── service-inventory
├── service-orders
├── service-payments
├── service-sales
├── service-notifications
├── service-reports
├── service-integrations
├── discovery-server
├── config-server
└── docker-compose

```

Additional services can be introduced as independent modules without coupling their implementation to the existing services.

---

## 🧩 Services

### User Service

The `service-user` module is responsible for user-related functionality.

Its current technology stack includes:

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Lombok
* JUnit 5

The service is structured to keep business rules separated from infrastructure concerns, allowing the domain model to remain independent from persistence and framework-specific implementations.

---

## 🛠️ Technology Stack

| Technology            | Purpose                         |
| --------------------- | ------------------------------- |
| **Java 21**           | Main programming language       |
| **Spring Boot 3.2.4** | Application framework           |
| **Spring Web**        | REST API development            |
| **Spring Data JPA**   | Persistence and ORM             |
| **PostgreSQL**        | Relational database             |
| **Gradle**            | Build and dependency management |
| **Lombok**            | Boilerplate reduction           |
| **JUnit 5**           | Automated testing               |

---

## 🎯 Architecture Principles

The project is being developed with the following principles in mind:

### Clean Architecture

Business rules should remain independent from external frameworks, databases, and delivery mechanisms.

```text
            ┌─────────────────────┐
            │     Controllers     │
            │      / REST API     │
            └──────────┬──────────┘
                       │
                       ▼
            ┌─────────────────────┐
            │    Application      │
            │      Use Cases      │
            └──────────┬──────────┘
                       │
                       ▼
            ┌─────────────────────┐
            │       Domain        │
            │ Business Rules      │
            └──────────┬──────────┘
                       │
                       ▼
            ┌─────────────────────┐
            │    Infrastructure   │
            │ JPA / PostgreSQL    │
            └─────────────────────┘
```

### Domain-Driven Design

The domain model represents business concepts independently from persistence models.

For example, domain objects should not need to know whether their data is persisted using JPA, PostgreSQL, or another technology.

### Dependency Inversion

Higher-level business rules should not depend directly on infrastructure implementations.

This makes the system easier to:

* test
* maintain
* refactor
* extend
* migrate to different infrastructure

---

## 🔄 Evolution Toward Microservices

The project is organized so that new business capabilities can become independent services.

A possible future architecture is:

```text
                         ┌─────────────────┐
                         │   API Gateway   │
                         └────────┬────────┘
                                  │
             ┌────────────────────┼────────────────────┐
             │                    │                    │
             ▼                    ▼                    ▼
      ┌─────────────┐      ┌─────────────┐      ┌─────────────┐
      │ User Service│      │Sales Service│      │Product      │
      │             │      │             │      │Service      │
      └──────┬──────┘      └──────┬──────┘      └──────┬──────┘
             │                    │                    │
             ▼                    ▼                    ▼
        PostgreSQL           PostgreSQL           PostgreSQL
```

Each service can own its own data and business rules, reducing coupling between bounded contexts.

---

## 📋 Planned Capabilities

The project can evolve to include capabilities such as:

* [x] User Service
* [ ] Authentication Service
* [ ] Product Service
* [ ] Inventory Service
* [ ] Sales Service
* [ ] Customer Service
* [ ] API Gateway
* [ ] Service-to-service communication
* [ ] Event-driven communication
* [ ] Authentication and authorization
* [ ] Observability
* [ ] Docker containers
* [ ] CI/CD pipeline
* [ ] Cloud deployment

---

## ▶️ Getting Started

### Prerequisites

Make sure the following tools are installed:

* Java 21
* Git
* PostgreSQL

Verify Java:

```bash
java -version
```

Verify Gradle through the included wrapper:

```bash
./gradlew --version
```

On Windows:

```powershell
.\gradlew.bat --version
```

---

### Clone the repository

```bash
git clone https://github.com/KleberVales/smart-sales-store.git
cd smart-sales-store
```

---

### Build the project

Linux/macOS:

```bash
./gradlew build
```

Windows:

```powershell
.\gradlew.bat build
```

---

### Run tests

Linux/macOS:

```bash
./gradlew test
```

Windows:

```powershell
.\gradlew.bat test
```

---

### Run the User Service

From the repository root:

```bash
./gradlew :service-user:bootRun
```

On Windows:

```powershell
.\gradlew.bat :service-user:bootRun
```

---

## 🗄️ Database

The User Service uses **PostgreSQL** for persistence through Spring Data JPA.

Database configuration should be provided through the application's configuration/environment variables rather than hard-coded credentials.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smart_sales_store
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

> For production environments, credentials should be supplied through environment variables or a dedicated secrets-management solution.

---

## 🧪 Testing

The project uses **JUnit 5** for automated tests.

Run the complete test suite with:

```bash
./gradlew test
```

As new use cases and services are introduced, tests should cover:

* Domain rules
* Application use cases
* Repository behavior
* REST endpoints
* Integration scenarios

---

## 📚 Learning Goals

This project is also a practical laboratory for studying backend architecture and distributed systems.

The main areas of study include:

* Java 21
* Spring Boot
* REST APIs
* Clean Architecture
* Hexagonal Architecture
* Domain-Driven Design
* Microservices
* PostgreSQL
* JPA
* Automated testing
* Docker
* CI/CD
* Cloud-native applications
* Event-driven architecture
* Distributed systems

---

### ✉️ Contact

Email: klebervales.dev@gmail.com  
LinkedIn: www.linkedin.com/in/kleber-vales

### Kleber Vales

**Java & Spring Software Engineer**

| Cloud | DevOps | Architectures | Generative AI | Methodologies |

🎓 **Bachelor's Degree in Computer Science**  
🎓 **MBA in Web Software Development**

**Certifications**  
🏆 **Oracle Certified Associate – Java SE 7 Programmer**  
🏆 **Microsoft MTA – Software Development Fundamentals**  
🏆 **Scrum Fundamentals Certified (SFC™)**  
🏆 **Oracle Cloud Infrastructure 2025 – DevOps Professional**  
🏆 **Oracle Cloud Infrastructure 2025 – Generative AI Professional**  
🏆 **Agentic AI Certified Fundations Associate**  
🏆 **OCI AI Foundations Associate**
