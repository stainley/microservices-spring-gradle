package com.salapp.microservices.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.salapp.microservices")
public class ReviewServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewServicesApplication.class, args);
    }

}
