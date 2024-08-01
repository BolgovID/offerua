package org.programming.pet.offerua.topic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.topic.UpdateTopicRequest;
import org.programming.pet.offerua.topic.persistence.TopicEntity;
import org.programming.pet.offerua.topic.TopicDto;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    TopicDto toDto(TopicEntity topic);


    @Mapping(source = "techId", target = "technologyId")
    TopicEntity toEntity(UpdateTopicRequest updateTopicRequest, UUID techId);
}
