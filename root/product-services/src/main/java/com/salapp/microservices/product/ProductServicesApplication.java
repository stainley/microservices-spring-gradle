package com.salapp.microservices.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starting point for Spring Boot Application
 */
@SpringBootApplication
@ComponentScan("com.salapp.microservices")
public class ProductServicesApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServicesApplication.class);


    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(ProductServicesApplication.class, args);
        String mongoDbHost = ctx.getEnvironment().getProperty("spring.data.mongodb.host");
        String mongoDbPort = ctx.getEnvironment().getProperty("spring.data.mongodb.port");


        LOG.info("Connected to MongoDB: " + mongoDbHost + ":" + mongoDbPort);
    }

}
