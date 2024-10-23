package org.programming.pet.offerua.question.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.domain.entities.QuestionEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    @Mapping(source = "topic", target = "topic")
    QuestionDto toDto(QuestionEntity questionEntity, String topic);

    QuestionEntity toEntity(QuestionUpdateRequest questionDto);
}
