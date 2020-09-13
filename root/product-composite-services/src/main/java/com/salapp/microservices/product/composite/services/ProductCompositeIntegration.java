package com.salapp.microservices.product.composite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.microservices.api.core.product.Product;
import com.salapp.microservices.api.core.product.ProductService;
import com.salapp.microservices.api.core.recommendation.Recommendation;
import com.salapp.microservices.api.core.recommendation.RecommendationService;
import com.salapp.microservices.api.core.review.Review;
import com.salapp.microservices.api.core.review.ReviewService;
import com.salapp.microservices.util.core.exceptions.InvalidInputException;
import com.salapp.microservices.util.core.exceptions.NotFoundException;
import com.salapp.microservices.util.core.http.HttpErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author Stainley Lebron
 * @since 4/6/20.
 */
@Component
public class ProductCompositeIntegration implements ProductService, ReviewService, RecommendationService {
    private final static String HTTP = "http://";
    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;

    private static Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);


    @Autowired
    public ProductCompositeIntegration(WebClient.Builder webClient, RestTemplate restTemplate, ObjectMapper mapper,
                                       @Value("${app.product-service.host}") String productServiceHost, @Value("${app.product-service.port}") int productServicePort,
                                       @Value("${app.review-service.host}") String reviewServiceHost, @Value("${app.review-service.port}") int reviewServicePort,
                                       @Value("${app.recommendation-service.host}") String recommendationServiceHost, @Value("${app.recommendation-service.port}") int recommendationServicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.webClient = webClient.build();

        productServiceUrl = HTTP + productServiceHost + ":" + productServicePort + "/product/";
        reviewServiceUrl = HTTP + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
        recommendationServiceUrl = HTTP + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation?productId=";
    }

/*    @Autowired
    public ProductCompositeIntegration(
            WebClient.Builder webClient,
            ObjectMapper mapper,

            @Value("${app.product-service.host}") String productServiceHost,
            @Value("${app.product-service.port}") int productServicePort,

            @Value("${app.recommendation-service.host}") String recommendationServiceHost,
            @Value("${app.recommendation-service.port}") int recommendationServicePort,

            @Value("${app.review-service.host}") String reviewServiceHost,
            @Value("${app.review-service.port}") int reviewServicePort
    ) {

        this.webClient = webClient.build();
        this.mapper = mapper;

        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort;
        recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort;
        reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort;
    }*/


    @Override
    public Product createProduct(Product body) {

        String url = productServiceUrl;
        LOG.debug("Will post a new product to URL: {}", url);

        Product product = restTemplate.postForObject(url, body, Product.class);
        LOG.debug("Create a product with id: {}", product.getProductId());

        return product;

    }

    @Override
    public Mono<Product> getProduct(int productId) {
        String url = productServiceUrl + "/product/" + productId;
        LOG.debug("Will call getProduct on URL: {}", url);

        return webClient.get().uri(url).retrieve().bodyToMono(Product.class)
                .log()
                .onErrorMap(WebClientResponseException.class, this::handleException);
    }

    @Override
    public void deleteProduct(int productId) {

        String url = productServiceUrl + "/" + productId;
        LOG.debug("Will call the deleteProduct API on URL: {}", url);

        restTemplate.delete(url);

    }

    @Override
    public Review createReview(Review body) {

        String url = reviewServiceUrl;
        LOG.debug("Will post a new review to URL: {}", url);

        Review review = restTemplate.postForObject(url, body, Review.class);
        LOG.debug("Create a review with id: {}", review.getProductId());

        return review;

    }


    @Override
    public List<Review> getReviews(int productId) {

        String url = reviewServiceUrl + "?productId=" + productId;

        LOG.debug("Will call he getReviews API on URL: {}", reviewServiceUrl);
        List<Review> reviews = restTemplate.exchange(url, GET, null, new ParameterizedTypeReference<List<Review>>() {
        }).getBody();

        LOG.debug("Found {} reviews for product with id: {}", reviews.size(), productId);
        return reviews;

    }


    @Override
    public void deleteReviews(int productId) {

        String url = reviewServiceUrl + "?productId=" + productId;
        LOG.debug(" Will call the deleteReviews API on URL: {}", url);

        restTemplate.delete(url);

    }

    @Override
    public Recommendation createRecommendation(Recommendation body) {

        String url = recommendationServiceUrl;
        LOG.debug("Will post a new recommendation to URL: {}", url);

        Recommendation recommendation = restTemplate.postForObject(url, body, Recommendation.class);
        LOG.debug("Created a recommendation with id: {}", recommendation.getProductId());

        return recommendation;

    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        try {
            String url = recommendationServiceUrl + "?productId=" + productId;

            LOG.debug("Will call getRecommendations API on URL: {}", url);

            List<Recommendation> recommendations = restTemplate.exchange(url, GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
            }).getBody();

            LOG.debug("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
            return recommendations;
        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting recommendations, return zero recommendations: {}", ex.getCause());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteRecommendation(int productId) {

        String url = recommendationServiceUrl + "?productId=" + productId;
        LOG.debug("Will call deleteRecommendation API on URL: {}", url);

        restTemplate.delete(url);


    }

    private Throwable handleException(Throwable ex) {

        if (!(ex instanceof WebClientResponseException)) {
            LOG.warn("Got a unexpected error: {}, will rethrow it", ex.toString());
            return ex;
        }

        WebClientResponseException wcre = (WebClientResponseException) ex;

        switch (wcre.getStatusCode()) {

            case NOT_FOUND:
                return new NotFoundException(getErrorMessage(wcre));

            case UNPROCESSABLE_ENTITY:
                return new InvalidInputException(getErrorMessage(wcre));

            default:
                LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
                LOG.warn("Error body: {}", wcre.getResponseBodyAsString());
                return ex;
        }
    }

    private String getErrorMessage(WebClientResponseException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ex.getMessage();
        }
    }

}
