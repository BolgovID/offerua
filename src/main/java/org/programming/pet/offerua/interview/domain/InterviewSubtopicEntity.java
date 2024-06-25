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
@Table(name = "interview_subtopic")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InterviewSubtopicEntity extends Auditable<String> {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "subtopic_name", unique = true, nullable = false)
    String subTopicName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_topic_id")
    List<QuestionEntity> questions;
}
