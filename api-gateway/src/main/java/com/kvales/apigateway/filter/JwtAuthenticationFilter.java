package com.kvales.apigateway.filter;

import com.kvales.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;

    // Public endpoints that don't require authentication
    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/refresh-token",
            "/actuator/health"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // Check if path is public
        if (isPublicEndpoint(path)) {
            log.info("Public endpoint accessed: {}", path);
            return chain.filter(exchange);
        }

        // Get Authorization header
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Missing or invalid Authorization header for: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            // Validate token
            if (!jwtUtil.isTokenValid(token)) {
                log.warn("Invalid JWT token for: {}", path);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Extract username and roles
            String username = jwtUtil.extractUsername(token);
            List<String> roles = jwtUtil.extractRoles(token);

            log.info("Authenticated user: {} with roles: {} for path: {}", username, roles, path);

            // Add user info to headers for downstream services
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Id", username)
                    .header("X-User-Roles", String.join(",", roles))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (Exception e) {
            log.error("Error validating JWT token: {}", e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean isPublicEndpoint(String path) {
        return PUBLIC_ENDPOINTS.stream().anyMatch(path::startsWith);
    }

    @Override
    public int getOrder() {
        return -100; // High priority
    }
}
