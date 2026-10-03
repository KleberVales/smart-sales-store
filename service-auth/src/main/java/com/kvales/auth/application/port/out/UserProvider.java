package com.kvales.auth.application.port.out;

public interface UserProvider {

    UserData findByEmail(String email);

    record UserData(
            Long id,
            String name,
            String email,
            String passwordHash
    ) {}

}
