package com.salapp.microservices.product.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starting point for Spring Boot Application
 */
@SpringBootApplication
@ComponentScan("com.salapp.microservices")
public class ProductServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServicesApplication.class, args);
    }

}
