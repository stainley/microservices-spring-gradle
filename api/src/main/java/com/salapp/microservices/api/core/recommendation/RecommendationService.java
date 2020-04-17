package com.salapp.microservices.api.core.recommendation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/6/20.
 */
public interface RecommendationService {


    /**
     * Sample usage:
     * curl -X POST $HOST:$PORT/recommendation \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"recommendationId":456,"author":"me","rate":5,"content":"yada, yada, yada"}'
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/recommendation", produces = "application/json", consumes = "application/json")
    Recommendation createRecommendation(@RequestBody Recommendation body);

    /**
     *  Sample usage: curl $HOST:$PORT/recommendation?productId=1
     * @param productId
     * @return
     */
    @GetMapping(value = "/recommendation", produces = "application/json")
    List<Recommendation> getRecommendations(@RequestParam(value = "productId", required = true) int productId);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/recommendation?productId=1
     *
     * @param productId
     */
    @DeleteMapping(value = "/recommendation")
    void deleteRecommendation(@RequestParam(value = "productId", required = true) int productId);
}
