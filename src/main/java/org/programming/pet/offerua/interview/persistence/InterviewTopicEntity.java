package org.programming.pet.offerua.interview.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "interview_topic")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InterviewTopicEntity extends Auditable<String> implements Serializable {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "topic_name", unique = true, nullable = false)
    String topicName;
    @Column(name = "topic_display_name", unique = true)
    String topicDisplayName;
}
