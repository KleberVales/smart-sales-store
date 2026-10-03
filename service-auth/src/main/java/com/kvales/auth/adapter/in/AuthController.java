package com.kvales.auth.adapter.in;

import com.kvales.auth.application.port.in.AuthenticateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticateUseCase authenticateUseCase;

    public AuthController(
            AuthenticateUseCase authenticateUseCase
    ) {
        this.authenticateUseCase = authenticateUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateUseCase.AuthenticateResult> login(
            @RequestBody LoginRequest request
    ) {

        var command =
                new AuthenticateUseCase.AuthenticateCommand(
                        request.email(),
                        request.password()
                );

        var result =
                authenticateUseCase.authenticate(command);

        return ResponseEntity.ok(result);
    }

    public record LoginRequest(
            String email,
            String password
    ) {
    }
}
