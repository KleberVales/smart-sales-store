package com.kvales.customers.application.usecase;


import com.kvales.customers.domain.exception.CustomerNotFoundException;
import com.kvales.customers.domain.model.Customer;
import com.kvales.customers.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    public void execute(Long id) {

        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customer.deactivate();

        customerRepository.save(customer);
    }
}