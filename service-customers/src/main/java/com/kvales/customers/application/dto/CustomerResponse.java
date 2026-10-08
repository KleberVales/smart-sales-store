package com.kvales.customers.application.dto;

import com.kvales.customers.domain.model.Customer;
import com.kvales.customers.domain.model.CustomerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        String name,
        String cpf,
        String email,
        String phone,
        LocalDate birthDate,
        CustomerStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
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
}
