package com.salapp.microservices.recommendation.services;

import com.salapp.microservices.api.core.recommendation.Recommendation;
import com.salapp.microservices.recommendation.peristence.RecommendationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 4/15/20.
 */
@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    @Mappings({
            @Mapping(target = "rate", source = "entity.rating"),
            @Mapping(target = "serviceAddress", ignore = true)
    })
    Recommendation entityToApi(RecommendationEntity entity);

    @Mappings({
            @Mapping(target = "rating", source = "api.rate"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    RecommendationEntity apiToEntity(Recommendation api);

    List<Recommendation> entityListToApi(List<RecommendationEntity> entity);
    List<RecommendationEntity> apiListToEntity(List<Recommendation> api);

}
