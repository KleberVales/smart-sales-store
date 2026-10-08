package com.kvales.customers.application.usecase;


import com.kvales.customers.application.dto.CustomerResponse;
import com.kvales.customers.application.dto.UpdateCustomerRequest;
import com.kvales.customers.domain.exception.CustomerNotFoundException;
import com.kvales.customers.domain.model.Customer;
import com.kvales.customers.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CustomerResponse execute(
            Long id,
            UpdateCustomerRequest request
    ) {

        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customer.update(
                request.name(),
                request.email(),
                request.phone(),
                request.birthDate()
        );

        Customer updatedCustomer =
                customerRepository.save(customer);

        return CustomerResponse.from(updatedCustomer);
    }
}
