## Store Automation

Store automation represents the application of software technologies to manage, integrate, and optimize the commercial operations of a retail establishment. The **Smart Sales Store** repository, developed by Kleber Vales, embodies this concept through a robust backend application focused on managing sales operations. The project is not limited to a simple registration system; it was architected to serve as a scalable, modular base, prepared to evolve from a modular monolith to a distributed microservices system, reflecting modern software engineering practices applied to the retail context.

The central purpose of the repository is to demonstrate how store automation can be built on solid architectural principles such as **Clean Architecture**, **Domain-Driven Design (DDD)**, and **modularity**. Instead of concentrating all business rules in a single block of code, the system isolates each business capability into independent modules, allowing the store to automate everything from user registration to payment processing and the generation of management reports. This approach ensures that automation does not become a bottleneck, but rather a strategic asset for business growth.

---

## Modular Architecture and Service Structure

Smart Sales Store adopts a **multi-module Gradle** structure, meaning that each store service can be developed, tested, and deployed independently. This architectural decision is fundamental to store automation, as it allows different sectors of the operation—such as sales, inventory, customers, and payments—to evolve at different paces without a change in one module breaking the others.

The project's directory structure reveals the scope of the intended automation:

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


Each folder represents an automatable business capability. The **service-users** folder, for example, is already implemented and uses Java 21, Spring Boot, Spring Web, Spring Data JPA, PostgreSQL, Lombok, and JUnit 5. It keeps business rules separate from the infrastructure, allowing the store's domain to remain independent of frameworks and databases. The other services—such as products, inventory, orders, payments, and reports—are planned to form a complete business automation ecosystem.

The figure below illustrates the layered separation that supports store automation in the project:

```text
┌─────────────────────┐
│     Controllers     │
│     / REST API      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│     Application     │
│      Use Cases      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│        Domain       │
│     Business Rules  │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   Infrastructure    │
│   JPA / PostgreSQL  │
└─────────────────────┘
```

This layered organization ensures that store automation is sustainable: business rules (such as discount calculation, inventory validation, or order flow) are isolated and can be tested without depending on the database or the REST interface.

---

## Evolution to Microservices and Distributed Automation

One of the most relevant points in the repository documentation is the preparation for the **evolution towards microservices**. In the context of store automation, this means that each operational function can become an autonomous service, with its own database and business rules. The project already includes an **API Gateway** as a single point of entry, a **discovery-server** for service location, and a **config-server** for centralized configuration management.





