package com.kvales.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(AuthMain.class, args);

    }

}