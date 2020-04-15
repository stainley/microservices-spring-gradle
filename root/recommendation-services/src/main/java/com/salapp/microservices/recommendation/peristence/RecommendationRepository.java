package com.salapp.microservices.recommendation.peristence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/11/20.
 */
public interface RecommendationRepository extends CrudRepository<RecommendationEntity, String> {

    List<RecommendationEntity> findByProductId(int productId);
}
