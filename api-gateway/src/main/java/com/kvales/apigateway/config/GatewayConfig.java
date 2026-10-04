package com.kvales.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("authServiceCB")
                                        .setFallbackUri("forward:/fallback/auth"))
                                .retry(config -> config
                                        .setRetries(3)
                                        .setStatuses(HttpStatus.SERVICE_UNAVAILABLE)))
                        .uri("http://localhost:8081"))

                .route("user-service", r -> r
                        .path("/api/users/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("userServiceCB")
                                        .setFallbackUri("forward:/fallback/users")))
                        .uri("http://localhost:8082"))

                .route("nutrition-service", r -> r
                        .path("/api/nutrition/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("nutritionServiceCB")
                                        .setFallbackUri("forward:/fallback/nutrition")))
                        .uri("http://localhost:8083"))
                .build();
    }
}
