package org.programming.pet.offerua.question.mapper;

import org.mapstruct.Mapper;
import org.programming.pet.offerua.question.domain.AnswerEntity;
import org.programming.pet.offerua.question.dto.AnswerDto;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerDto toDto(AnswerEntity answerEntity);
}
