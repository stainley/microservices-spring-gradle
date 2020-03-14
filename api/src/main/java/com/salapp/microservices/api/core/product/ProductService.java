package com.salapp.microservices.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Product services Rest Interface
 */
public interface ProductService {

    /**
     *  Get a product by productId
     * @param productId String
     * @return Product
     */
    @GetMapping(value = "/product/{productId}", produces = "application/json")
    Product getProduct(@PathVariable String productId);
}
