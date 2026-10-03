package com.kvales.auth.application.port.out;

public interface TokenGenerator {

    String generate(UserProvider.UserData user);
}
