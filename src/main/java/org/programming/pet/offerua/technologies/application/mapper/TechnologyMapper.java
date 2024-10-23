package org.programming.pet.offerua.technologies.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyUpdateRequest;
import org.programming.pet.offerua.technologies.TechnologyWithQuestionCountDto;
import org.programming.pet.offerua.technologies.domain.entity.TechnologyEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TechnologyMapper {

    TechnologyEntity toEntity(TechnologyUpdateRequest technologyUpdateRequest);

    TechnologyDto toDto(TechnologyEntity technologyEntity);

    TechnologyWithQuestionCountDto toDtoWithQuestionCount(TechnologyDto technologyDto, Long questionCount);

}
