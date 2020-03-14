package com.salapp.microservices.api.core.product;

/**
 * Product information
 */
public class Product {
    private final String productId;
    private final String name;
    private final int weight;
    private final String serviceAddress;

    public Product(String productId, String name, int weight, String serviceAddress) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.serviceAddress = serviceAddress;
    }
}
