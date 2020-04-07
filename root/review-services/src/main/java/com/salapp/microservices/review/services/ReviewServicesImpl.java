package com.salapp.microservices.review.services;

import com.salapp.microservices.api.core.review.Review;
import com.salapp.microservices.api.core.review.ReviewService;
import com.salapp.microservices.util.core.exceptions.InvalidInputException;
import com.salapp.microservices.util.core.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/7/20.
 */
@RestController
public class ReviewServicesImpl implements ReviewService {

    private final ServiceUtil serviceUtil;
    private final Logger LOG = LoggerFactory.getLogger(ReviewServicesImpl.class);


    @Autowired
    public ReviewServicesImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Review> getReviews(int productId) {

        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 123) {
            LOG.debug("No reviews found for productId: {}", productId);
            return new ArrayList<>();
        }
        List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author-1", "Subject-1", "Content-1", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 2, "Author-2", "Subject-2", "Content-2", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 3, "Author-3", "Subject-3", "Content-3", serviceUtil.getServiceAddress()));

        LOG.debug("/reviews response size:{}", list.size());
        return list;
    }
}
