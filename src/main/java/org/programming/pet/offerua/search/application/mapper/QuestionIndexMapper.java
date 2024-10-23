package org.programming.pet.offerua.search.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.search.domain.QuestionIndex;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionIndexMapper {

    @Mapping(source = "technologyId", target = "id")
    @Mapping(source = "topicName", target = "topic")
    @Mapping(source = "question", target = "question")
    QuestionDto toQuestionDto(QuestionIndex questionIndex);
}
