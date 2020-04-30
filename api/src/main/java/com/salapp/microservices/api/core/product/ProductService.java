package com.salapp.microservices.api.core.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    Mono<Product> getProduct(@PathVariable int productId);

    /**
     * +
     * curl -X POST $HOST:$PORT/product \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"name":"product 123","weight":123}'
     * @param body
     * @return
     */
    @PostMapping(value = "/product", consumes = "application/json", produces = "application/json")
    Product createProduct(@RequestBody Product body);

    /**
     * Sample usage:
     * curl -X DELETE $HOST:$PORT/product/1
     *
     * @param productId
     */
    @DeleteMapping(value = "/product/{productId}")
    void deleteProduct(@PathVariable int productId);
}
