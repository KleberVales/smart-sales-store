# API Gateway

Smart Sales Store | module api-gateway | Java 21, Spring Boot 3.3.4, Spring Cloud Gateway 2023.0.3

## 1. Overview
    
The API Gateway is the single entry point to the Smart Sales Store backend. Clients such as the React frontend never call a microservice directly: every request goes to the gateway on port 8080, which authenticates it, picks the right service from the URL path, and protects the system from failing downstream services. It is built on Spring Cloud Gateway (reactive, non-blocking) and runs as its own Gradle module and Docker image.

Responsibilities

- Routing of `/api/**` paths to the auth, user, customers and products services.
- Authentication: validates JWT bearer tokens and passes the caller's identity downstream.
- Resilience: circuit breakers with fallback responses, plus retries on the auth route.
- Cross-cutting concerns: CORS policy, Actuator health and route inspection endpoints.

## 2. Architecture

<img width="1542" height="603" alt="image" src="https://github.com/user-attachments/assets/374efe75-444e-4fc1-a86a-db384eec98fb" />
Figure 1. Request path from client through the gateway filter chain to the downstream services.

## 3. Route table

<img width="1219" height="173" alt="Screenshot from 2026-10-18 16-44-38" src="https://github.com/user-attachments/assets/877cf8b1-7176-418d-ac89-4a4dadcd7b67" />

Paths are forwarded unchanged (no prefix stripping), so GET /api/products/12 on the gateway reaches GET /api/products/12 on service-products. When a circuit opens or a call fails, the request is forwarded to the matching /fallback/* endpoint, which returns JSON of the form {"status": 503, "message": "... Service is currently unavailable", "timestamp": ...}.

## 4. Authentication and security

<img width="1548" height="394" alt="image" src="https://github.com/user-attachments/assets/a075e9f5-5ccd-448f-bf9d-a39295b91966" />
Figure 2. Decision flow of JwtAuthenticationFilter (a global filter, order -100).

Tokens are verified with the shared secret configured in jwt. secret (an HMAC key of at least 32 characters) and must not be expired. After a successful check, the gateway adds two headers that downstream services can trust and use without re-parsing the token:




