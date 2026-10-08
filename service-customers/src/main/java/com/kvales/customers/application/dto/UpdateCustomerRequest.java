package com.kvales.customers.application.dto;

import java.time.LocalDate;

public record UpdateCustomerRequest(
        String name,
        String email,
        String phone,
        LocalDate birthDate
) {
}