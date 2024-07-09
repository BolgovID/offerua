package org.programming.pet.offerua.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.interview.persistence.InterviewTopicEntity;
import org.programming.pet.offerua.interview.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.interview.InterviewTopicDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InterviewTopicMapper {

    @Mapping(source = "displayName", target = "topicDisplayName")
    InterviewTopicEntity toInterviewTopicEntity(InterviewTopicUpdateRequest interviewTopicUpdateRequest);

    @Mapping(source = "topicDisplayName", target = "displayName")
    InterviewTopicDto toInterviewTopicDto(InterviewTopicEntity interviewTopicEntity);
}
