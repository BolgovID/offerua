package org.programming.pet.offerua.answers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.answers.persistence.AnswerEntity;
import org.programming.pet.offerua.answers.AnswerDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {

    AnswerDto toDto(AnswerEntity answerEntity);
}
