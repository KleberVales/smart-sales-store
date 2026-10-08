package com.kvales.customers.adapter.out.persistence;

import com.kvales.customers.domain.model.Customer;
import com.kvales.customers.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter
        implements CustomerRepository {

    private final JpaCustomerRepository repository;

    @Override
    public Customer save(Customer customer) {

        CustomerEntity entity = toEntity(customer);

        CustomerEntity savedEntity = repository.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(Long id) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {

        return repository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {

        return repository.findByCpf(cpf)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public void delete(Customer customer) {

        if (customer.getId() == null) {
            return;
        }

        repository.deleteById(customer.getId());
    }

    private CustomerEntity toEntity(Customer customer) {

        return new CustomerEntity(
                customer.getName(),
                customer.getCpf(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getBirthDate(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    private Customer toDomain(CustomerEntity entity) {
        return Customer.restore(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBirthDate(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
