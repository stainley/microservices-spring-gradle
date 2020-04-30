package com.salapp.microservices.product.services;

import com.salapp.microservices.product.peristence.ProductEntity;
import com.salapp.microservices.product.peristence.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Stainley Lebron
 * @since 4/11/20.
 */
@DataMongoTest
public class PersistenceTests {

    @Autowired
    private ProductRepository repository;

    private ProductEntity savedEntity;

    @BeforeEach
    public void setupDb() {
        StepVerifier.create(repository.deleteAll()).verifyComplete();

        ProductEntity entity = new ProductEntity(1, "n", 1);
        StepVerifier.create(repository.save(entity))
                .expectNextMatches(createdEntity -> {
                    savedEntity = createdEntity;
                    return areProductEqual(entity, savedEntity);
                }).verifyComplete();
    }

    //@Test
    public void create() {
        ProductEntity newEntity = new ProductEntity(2, "n", 2);

        StepVerifier.create(repository.save(newEntity))
                .expectNextMatches(createdEntity -> newEntity.getProductId() == createdEntity.getProductId())
                .verifyComplete();

        StepVerifier.create(repository.findById(newEntity.getId()))
                .expectNextMatches(foundEntity -> areProductEqual(newEntity, foundEntity))
                .verifyComplete();

        StepVerifier.create(repository.count()).expectNext(2L).verifyComplete();

    }


    private boolean areProductEqual(ProductEntity expectedEntity, ProductEntity actualEntity) {
        return
                (expectedEntity.getId().equals(actualEntity.getId())) &&
                        (expectedEntity.getVersion() == actualEntity.getVersion()) &&
                        (expectedEntity.getProductId() == actualEntity.getProductId()) &&
                        (expectedEntity.getName().equals(actualEntity.getName())) &&
                        (expectedEntity.getWeight() == actualEntity.getWeight());
    }


    private void assertEqualsProduct(ProductEntity expectedEntity, ProductEntity actualEntity) {
        assertEquals(expectedEntity.getId(), actualEntity.getId());
        assertEquals(expectedEntity.getVersion(), actualEntity.getVersion());
        assertEquals(expectedEntity.getProductId(), actualEntity.getProductId());
        assertEquals(expectedEntity.getName(), actualEntity.getName());
        assertEquals(expectedEntity.getWeight(), actualEntity.getWeight());
    }
}
