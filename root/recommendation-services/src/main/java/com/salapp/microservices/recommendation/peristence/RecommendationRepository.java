package com.salapp.microservices.recommendation.peristence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author Stainley Lebron
 * @since 4/11/20.
 */
public interface RecommendationRepository extends ReactiveCrudRepository<RecommendationEntity, String> {

    Flux<RecommendationEntity> findByProductId(int productId);
}
