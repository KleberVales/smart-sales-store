package com.kvales.auth.application.port.out;

public interface PasswordHasher {

    boolean matches(String rawPassword, String passwordHash);

}
