package com.salapp.microservices.product.peristence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author Stainley Lebron
 * @since 4/11/20.
 */

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, String> {

    Mono<ProductEntity> findByProductId(int productId);
}
