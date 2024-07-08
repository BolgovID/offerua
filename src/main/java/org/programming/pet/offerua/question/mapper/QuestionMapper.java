package org.programming.pet.offerua.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.persistence.QuestionEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    QuestionDto toDto(QuestionEntity questionEntity);
}
