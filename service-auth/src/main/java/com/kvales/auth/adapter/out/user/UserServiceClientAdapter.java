package com.kvales.auth.adapter.out.user;

import com.kvales.auth.application.port.out.UserProvider;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientAdapter implements UserProvider {

    private final UserServiceClient client;

    public UserServiceClientAdapter(UserServiceClient client) {
        this.client = client;
    }

    @Override
    public UserData findByEmail(String email) {

        var response = client.findByEmail(email);

        return new UserData(
                response.id(),
                response.name(),
                response.email(),
                response.passwordHash()
        );
    }
}
