package org.programming.pet.offerua.question.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;
import org.programming.pet.offerua.interview.domain.InterviewSubtopicEntity;
import org.programming.pet.offerua.interview.domain.InterviewTopicEntity;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "question")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity extends Auditable<String> {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "question", nullable = false)
    String question;
    @Column(name = "probability")
    Double probability;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    List<AnswerEntity> answers;
    @ManyToOne
    @JoinColumn(name = "interview_topic_id")
    InterviewTopicEntity interviewTopic;
    @ManyToOne
    @JoinColumn(name = "subtopic_id")
    InterviewSubtopicEntity subTopic;
}
