package com.kvales.user.application.port.in;

public interface RegisterUserUseCase {

    RegisterUserResult registerUser(
            RegisterUserCommand command
    );

    record RegisterUserCommand(
            String name,
            String email,
            String password
    ) {
    }

    record RegisterUserResult(
            Long id,
            String name,
            String email
    ) {
    }
}