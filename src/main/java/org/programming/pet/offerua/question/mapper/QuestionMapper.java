package org.programming.pet.offerua.question.mapper;

import org.mapstruct.Mapper;
import org.programming.pet.offerua.question.domain.QuestionEntity;
import org.programming.pet.offerua.question.dto.QuestionDto;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDto toDto(QuestionEntity questionEntity);
}
