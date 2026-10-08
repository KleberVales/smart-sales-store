package com.kvales.customers.application.dto;

import java.time.LocalDate;

public record CreateCustomerRequest(
        String name,
        String cpf,
        String email,
        String phone,
        LocalDate birthDate
) {
}
