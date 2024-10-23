package org.programming.pet.offerua.question.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "question", indexes = @Index(columnList = "last_modified_date"))
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity extends Auditable<String> implements Serializable {
    @Id
    @UuidGenerator
    UUID id;
    @Column(name = "question", nullable = false)
    String question;
    @Column(name = "probability")
    Double probability;
    @Column(name = "technology_topic_id")
    UUID topicId;
}