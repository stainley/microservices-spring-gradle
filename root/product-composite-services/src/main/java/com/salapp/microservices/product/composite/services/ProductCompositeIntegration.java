package com.salapp.microservices.product.composite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.microservices.api.core.product.Product;
import com.salapp.microservices.api.core.product.ProductService;
import com.salapp.microservices.api.core.recommendation.Recommendation;
import com.salapp.microservices.api.core.recommendation.RecommendationService;
import com.salapp.microservices.api.core.review.Review;
import com.salapp.microservices.api.core.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/6/20.
 */
@Component
public class ProductCompositeIntegration implements ProductService, ReviewService, RecommendationService {
    private final static String HTTP = "http://";
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;


    @Autowired
    public ProductCompositeIntegration(RestTemplate restTemplate, ObjectMapper mapper,
                                       @Value("${app.product-service.host}") String productServiceHost, @Value("${app.product-service.port}") int productServicePort,
                                       @Value("${app.review-service.host}") String reviewServiceHost, @Value("${app.review-service.port}") int reviewServicePort,
                                       @Value("${app.recommendation-service.host}") String recommendationServiceHost, @Value("${app.recommendation-service.port}") int recommendationServicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        productServiceUrl = HTTP + productServiceHost + ":" + productServicePort + "/product/";
        reviewServiceUrl = HTTP + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
        recommendationServiceUrl = HTTP + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation?productId=";
    }

    @Override
    public Product getProduct(int productId) {
        String url = productServiceUrl + productId;

        return restTemplate.getForObject(url, Product.class);
    }

    @Override
    public Product createProduct(Product body) {
        return null;
    }

    @Override
    public void deleteProduct(int productId) {

    }

    @Override
    public List<Review> getReviews(int productId) {
        String url = reviewServiceUrl + productId;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
        }).getBody();
    }

    @Override
    public Review createReview(Review body) {
        return null;
    }

    @Override
    public void deleteReviews(int productId) {

    }

    @Override
    public Recommendation createRecommendation(Recommendation body) {
        return null;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        String url = recommendationServiceUrl + productId;

        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
        }).getBody();
    }

    @Override
    public void deleteRecommendation(int productId) {

    }

}
