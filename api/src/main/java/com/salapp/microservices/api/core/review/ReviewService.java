package com.salapp.microservices.api.core.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/6/20.
 */
public interface ReviewService {

    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/review", produces = "application/json")
    List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);

    /**
     * curl -X POST $HOST:$PORT/review \
     * -H "Content-Type: application/json" --data \
     * '{"productId":123,"reviewId":456,"author":"me","subject":"yada, yada, yada","content":"yada, yada, yada"}'
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/review", consumes = "application/json", produces = "application/json")
    Review createReview(@RequestBody Review body);

    /**
     * Sample usage:
     * curl -X DELETE $HOST:$PORT/review/1
     *
     * @param productId
     */
    @DeleteMapping(value = "/review")
    void deleteReviews(@RequestParam(value = "productId", required = true) int productId);
}
