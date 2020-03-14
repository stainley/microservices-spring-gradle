package com.salapp.microservices.product.services.product;

import com.salapp.microservices.api.core.product.Product;
import com.salapp.microservices.api.core.product.ProductService;

/**
 * This class implements REST information from @see
 * {@link ProductServiceImpl}
 */
public class ProductServiceImpl implements ProductService {

    public void nothing(){
        System.out.println("Nothing");
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }
}
