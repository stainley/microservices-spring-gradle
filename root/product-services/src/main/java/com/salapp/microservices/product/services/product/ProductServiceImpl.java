package com.salapp.microservices.product.services.product;

import com.salapp.microservices.api.core.product.Product;
import com.salapp.microservices.api.core.product.ProductService;
import com.salapp.microservices.util.core.exceptions.InvalidInputException;
import com.salapp.microservices.util.core.exceptions.NotFoundException;
import com.salapp.microservices.util.core.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements REST information from @see
 * {@link ProductServiceImpl}
 */
@RestController
public class ProductServiceImpl implements ProductService {
    private final static Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }


    @Override
    public Product getProduct(int productId) {
        LOG.debug("/product return the found product for productId={}", productId);

        if (productId < 1) throw new InvalidInputException("Invalid productId:" + productId);

        if (productId == 13) throw new NotFoundException("No product found for productId:" + productId);

        return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
    }

}
