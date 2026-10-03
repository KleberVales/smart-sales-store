package com.kvales.user.adapter.in;

import com.kvales.user.application.port.in.RegisterUserUseCase;
import com.kvales.user.application.port.in.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserController(
            RegisterUserUseCase registerUserUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserUseCase.RegisterUserResult>
    register(
            @RequestBody RegisterUserRequest request
    ) {

        var command =
                new RegisterUserUseCase.RegisterUserCommand(
                        request.name(),
                        request.email(),
                        request.password()
                );

        var result =
                registerUserUseCase.registerUser(command);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<RegisterUserUseCase.UserResult>
    getByEmail(
            @RequestParam String email
    ) {
        var result =
                registerUserUseCase.getUserByEmail(email);


        return ResponseEntity.ok(result);
    }

    public record RegisterUserRequest(
            String name,
            String email,
            String password
    ) {
    }
}
