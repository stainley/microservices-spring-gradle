package com.salapp.microservices.recommendation.services;

import com.salapp.microservices.api.core.recommendation.Recommendation;
import com.salapp.microservices.api.core.recommendation.RecommendationService;
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
public class RecommendationServiceIml implements RecommendationService {
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceIml.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public RecommendationServiceIml(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {

        if (productId < 1) throw new InvalidInputException("Invalid productId:" + productId);

        if (productId == 123) {
            LOG.debug("No recommendations found for productId: {}", productId);
            return new ArrayList<>();
        }

        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content-1", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content-2", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content-3", serviceUtil.getServiceAddress()));

        LOG.debug("/recommendation response size: {}", list.size());
        return list;
    }
}
