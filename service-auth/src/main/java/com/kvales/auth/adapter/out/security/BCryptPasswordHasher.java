package com.kvales.auth.adapter.out.security;

import com.kvales.auth.application.port.out.PasswordHasher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasher implements PasswordHasher {

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @Override
    public boolean matches(
            String rawPassword,
            String passwordHash
    ) {
        return encoder.matches(rawPassword, passwordHash);
    }
}
