package com.kvales.auth.adapter.out.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "service-user",
        url = "${service-user.url}"
)
public interface UserServiceClient {

    @GetMapping("/api/users")
    UserResponse findByEmail(
            @RequestParam String email
    );

    record UserResponse(
            Long id,
            String name,
            String email,
            String passwordHash
    ) {}
}