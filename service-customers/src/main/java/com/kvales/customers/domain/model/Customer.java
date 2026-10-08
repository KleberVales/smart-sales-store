package com.kvales.customers.domain.model;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Customer {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private CustomerStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer(
            String name,
            String cpf,
            String email,
            String phone,
            LocalDate birthDate
    ) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.status = CustomerStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public void update(
            String name,
            String email,
            String phone,
            LocalDate birthDate
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = CustomerStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = CustomerStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        return this.status == CustomerStatus.ACTIVE;
    }

    public static Customer restore(
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
        Customer customer = new Customer(
                name,
                cpf,
                email,
                phone,
                birthDate
        );

        customer.id = id;
        customer.status = status;
        customer.createdAt = createdAt;
        customer.updatedAt = updatedAt;

        return customer;
    }
}