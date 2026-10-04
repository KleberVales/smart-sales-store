package com.kvales.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth")
    public Mono<Map<String, Object>> authFallback() {
        return Mono.just(createFallbackResponse("Auth Service is currently unavailable"));
    }

    @GetMapping("/users")
    public Mono<Map<String, Object>> usersFallback() {
        return Mono.just(createFallbackResponse("User Service is currently unavailable"));
    }

    @GetMapping("/nutrition")
    public Mono<Map<String, Object>> nutritionFallback() {
        return Mono.just(createFallbackResponse("Nutrition Service is currently unavailable"));
    }

    private Map<String, Object> createFallbackResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 503);
        response.put("message", message);
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}
