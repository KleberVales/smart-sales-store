package com.kvales.user.adapter.out.persistence;

import com.kvales.user.application.port.out.UserRepository;
import com.kvales.user.domain.User;
import com.kvales.user.application.port.out.UserRepository;
import com.kvales.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPersistenceAdapter
        implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(
            SpringDataUserRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {

        UserJpaEntity entity =
                new UserJpaEntity(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword()
                );

        UserJpaEntity saved =
                repository.save(entity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPassword()
        );
    }

    @Override
    public Optional<User> findByEmail(
            String email
    ) {

        return repository
                .findByEmail(email)
                .map(entity ->
                        new User(
                                entity.getId(),
                                entity.getName(),
                                entity.getEmail(),
                                entity.getPassword()
                        )
                );
    }

    @Override
    public boolean existsByEmail(
            String email
    ) {
        return repository.existsByEmail(email);
    }
}
