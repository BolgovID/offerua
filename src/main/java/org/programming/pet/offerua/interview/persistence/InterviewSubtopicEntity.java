package org.programming.pet.offerua.interview.persistence;

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
}
