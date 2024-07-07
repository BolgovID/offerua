package org.programming.pet.offerua.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.question.domain.AnswerEntity;
import org.programming.pet.offerua.question.dto.AnswerDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {

    AnswerDto toDto(AnswerEntity answerEntity);
}
