package com.salapp.microservices.product.peristence;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * @author Stainley Lebron
 * @since 4/11/20.
 */

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, String> {

    Optional<ProductEntity> findByProductId(int productId);
}
