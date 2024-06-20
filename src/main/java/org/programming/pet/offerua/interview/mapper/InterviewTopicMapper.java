package org.programming.pet.offerua.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.programming.pet.offerua.interview.domain.InterviewTopicEntity;
import org.programming.pet.offerua.interview.dto.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.interview.dto.InterviewTopicDto;

@Mapper(componentModel = "spring")
public interface InterviewTopicMapper {

    @Mapping(source = "displayName", target = "topicDisplayName")
    InterviewTopicEntity toInterviewTopicEntity(InterviewTopicUpdateRequest interviewTopicUpdateRequest);

    @Mapping(source = "topicDisplayName", target = "displayName")
    InterviewTopicDto toInterviewTopicDto(InterviewTopicEntity interviewTopicEntity);
}
