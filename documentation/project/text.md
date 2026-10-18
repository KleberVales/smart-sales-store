# API Gateway

Smart Sales Store | module api-gateway | Java 21, Spring Boot 3.3.4, Spring Cloud Gateway 2023.0.3

## 1. Overview
    
The API Gateway is the single entry point to the Smart Sales Store backend. Clients such as the React frontend never call a microservice directly: every request goes to the gateway on port 8080, which authenticates it, picks the right service from the URL path, and protects the system from failing downstream services. It is built on Spring Cloud Gateway (reactive, non-blocking) and runs as its own Gradle module and Docker image.

Responsibilities

◦ Routing of /api/** paths to the auth, user, customers and products services.




