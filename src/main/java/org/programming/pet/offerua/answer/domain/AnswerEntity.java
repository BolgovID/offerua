package org.programming.pet.offerua.answer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
