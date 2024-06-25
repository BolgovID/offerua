package org.programming.pet.offerua.question.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;

import java.util.UUID;

@Entity
@Table(name = "answer")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity extends Auditable<String> {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "answer", nullable = false)
    String answer;
    Integer rating;
    @ManyToOne
    @JoinColumn(name = "question_id")
    QuestionEntity question;
}
