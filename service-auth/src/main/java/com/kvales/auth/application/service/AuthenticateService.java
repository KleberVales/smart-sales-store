package com.kvales.auth.application.service;

import com.kvales.auth.application.port.in.AuthenticateUseCase;
import org.springframework.stereotype.Service;

import com.kvales.auth.application.port.out.UserProvider;
import com.kvales.auth.application.port.out.PasswordHasher;
import com.kvales.auth.application.port.out.TokenGenerator;

@Service
public class AuthenticateService
        implements AuthenticateUseCase {

    private final UserProvider userProvider;
    private final PasswordHasher passwordHasher;
    private final TokenGenerator tokenGenerator;

    public AuthenticateService(
            UserProvider userProvider,
            PasswordHasher passwordHasher,
            TokenGenerator tokenGenerator
    ) {
        this.userProvider = userProvider;
        this.passwordHasher = passwordHasher;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthenticateResult authenticate(
            AuthenticateCommand command
    ) {

        var user =
                userProvider.findByEmail(command.email());

        if (!passwordHasher.matches(
                command.password(),
                user.passwordHash()
        )) {
            throw new RuntimeException("Invalid credentials");
        }

        var token = tokenGenerator.generate(user);

        return new AuthenticateResult(token);
    }
}
