package com.salapp.microservices.review.services;

import com.salapp.microservices.api.core.review.Review;
import com.salapp.microservices.review.persistence.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/13/20.
 */
@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mappings(
            @Mapping(target = "serviceAddress", ignore = true)
    )
    Review entityToApi(ReviewEntity entity);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    ReviewEntity apiToEntity(Review api);

    List<Review> entityListToApiList(List<ReviewEntity> entity);

    List<ReviewEntity> apiListToEntityList(List<Review> api);
}
