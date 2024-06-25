package org.programming.pet.offerua.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.programming.pet.offerua.question.domain.QuestionEntity;
import org.programming.pet.offerua.question.dto.QuestionDto;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "subTopic.subTopicName", target = "subTopic")
    QuestionDto toDto(QuestionEntity questionEntity);
}
