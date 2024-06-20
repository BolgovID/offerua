package org.programming.pet.offerua.interview.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;
import org.programming.pet.offerua.question.domain.QuestionEntity;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "interview_topic")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InterviewTopicEntity extends Auditable<String> {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "topic_name", unique = true, nullable = false)
    String topicName;
    @Column(name = "topic_display_name", unique = true)
    String topicDisplayName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_topic_id")
    List<QuestionEntity> questions;
}
