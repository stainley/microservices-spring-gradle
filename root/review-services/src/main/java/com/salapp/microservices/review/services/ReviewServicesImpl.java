package com.salapp.microservices.review.services;

import com.salapp.microservices.api.core.review.Review;
import com.salapp.microservices.api.core.review.ReviewService;
import com.salapp.microservices.review.persistence.ReviewEntity;
import com.salapp.microservices.review.persistence.ReviewRepository;
import com.salapp.microservices.util.core.exceptions.InvalidInputException;
import com.salapp.microservices.util.core.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/7/20.
 */
@RestController
public class ReviewServicesImpl implements ReviewService {


    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    private final ServiceUtil serviceUtil;
    private final Logger LOG = LoggerFactory.getLogger(ReviewServicesImpl.class);


    @Autowired
    public ReviewServicesImpl(ReviewRepository repository, ReviewMapper mapper, ServiceUtil serviceUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }


    @Override
    public Review createReview(Review body) {
        try {
            ReviewEntity entity = mapper.apiToEntity(body);
            ReviewEntity newEntity = repository.save(entity);

            LOG.debug("createReview: created a review entity:{}/{}", body.getProductId(), body.getReviewId());
            return mapper.entityToApi(newEntity);
        } catch (DataIntegrityViolationException dive) {
            throw new InvalidInputException("Duplicate key, Product Id: " + body.getProductId() + ", Review Id: " + body.getReviewId());
        }
    }

    @Override
    public List<Review> getReviews(int productId) {

        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        List<ReviewEntity> entityList = repository.findByProductId(productId);
        List<Review> list = mapper.entityListToApiList(entityList);
        list.forEach(e -> e.setServiceAddress(serviceUtil.getServiceAddress()));

        LOG.debug("/reviews response size:{}", list.size());

        return list;
    }


    @Override
    public void deleteReviews(int productId) {
        LOG.debug("deleteReviews: tries to delete reviews for the product with productId: {}", productId);
        repository.deleteAll(repository.findByProductId(productId));
    }

}
