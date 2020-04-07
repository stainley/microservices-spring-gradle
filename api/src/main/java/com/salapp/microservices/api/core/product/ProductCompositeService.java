package com.salapp.microservices.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Stainley Lebron
 * @since 3/26/20.
 */
public interface ProductCompositeService {

    @GetMapping(value = "/product-composite/{productId}")
    ProductAggregate getProduct(@PathVariable int productId);
}
