package com.salapp.microservices.api.core.recommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/6/20.
 */
public interface RecommendationService {

    /**
     *  Sample usage: curl $HOST:$PORT/recommendation?productId=1
     * @param productId
     * @return
     */
    @GetMapping(value = "/recommendation", produces = "application/json")
    List<Recommendation> getRecommendations(@RequestParam(value = "productId", required = true) int productId);
}
