package org.programming.pet.offerua.answers.persistence;

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
@Table(name = "answer")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity extends Auditable<String> implements Serializable {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "answer", nullable = false)
    String answer;
    Integer rating;
    @Column(name = "question_id")
    UUID questionId;
}
