package com.kvales.auth.application.port.in;

public interface AuthenticateUseCase {

    AuthenticateResult authenticate(AuthenticateCommand command);

    record AuthenticateCommand(
            String email,
            String password
    ) {
    }

    record AuthenticateResult(
            String token
    ) {
    }


}
