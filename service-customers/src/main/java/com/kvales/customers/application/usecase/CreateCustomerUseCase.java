package com.kvales.customers.application.usecase;


import com.kvales.customers.application.dto.CreateCustomerRequest;
import com.kvales.customers.application.dto.CustomerResponse;
import com.kvales.customers.domain.exception.CustomerAlreadyExistsException;
import com.kvales.customers.domain.model.Customer;
import com.kvales.customers.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CustomerResponse execute(CreateCustomerRequest request) {

        if (customerRepository.existsByCpf(request.cpf())) {
            throw new CustomerAlreadyExistsException(
                    "CPF",
                    request.cpf()
            );
        }

        if (customerRepository.existsByEmail(request.email())) {
            throw new CustomerAlreadyExistsException(
                    "email",
                    request.email()
            );
        }

        Customer customer = new Customer(
                request.name(),
                request.cpf(),
                request.email(),
                request.phone(),
                request.birthDate()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponse.from(savedCustomer);
    }
}