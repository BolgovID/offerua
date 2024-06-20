package org.programming.pet.offerua.question.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;

import java.util.UUID;

@Entity
@Table(name = "question_topic")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTopicEntity extends Auditable<String> {
    @Id
    @UuidGenerator
    UUID id;
    String name;
}