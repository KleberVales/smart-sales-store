package com.kvales.customers.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String field, String value) {
        super("Customer already exists with " + field + ": " + value);
    }
}
