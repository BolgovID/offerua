package org.programming.pet.offerua.topic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.topic.persistence.InterviewTopicEntity;
import org.programming.pet.offerua.topic.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.topic.InterviewTopicDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InterviewTopicMapper {

    @Mapping(source = "displayName", target = "topicDisplayName")
    InterviewTopicEntity toInterviewTopicEntity(InterviewTopicUpdateRequest interviewTopicUpdateRequest);

    @Mapping(source = "topicDisplayName", target = "displayName")
    InterviewTopicDto toInterviewTopicDto(InterviewTopicEntity interviewTopicEntity);
}
