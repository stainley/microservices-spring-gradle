package com.salapp.microservices.recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.salapp.microservices")
public class RecommendationServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationServicesApplication.class, args);
    }

}
