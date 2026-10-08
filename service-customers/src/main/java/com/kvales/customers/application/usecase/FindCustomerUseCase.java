package com.kvales.customers.application.usecase;


import com.kvales.customers.application.dto.CustomerResponse;
import com.kvales.customers.domain.exception.CustomerNotFoundException;
import com.kvales.customers.domain.model.Customer;
import com.kvales.customers.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CustomerResponse findById(Long id) {

        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return CustomerResponse.from(customer);
    }

    public CustomerResponse findByEmail(String email) {

        Customer customer = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with email: " + email
                ));

        return CustomerResponse.from(customer);
    }
}