package org.programming.pet.offerua.search.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionIndexMapper {

    QuestionIndex toQuestionIndex(QuestionDto questionDto);
}
