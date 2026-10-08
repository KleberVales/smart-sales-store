package com.kvales.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CustomersApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CustomersApplication.class, args);

    }

}
