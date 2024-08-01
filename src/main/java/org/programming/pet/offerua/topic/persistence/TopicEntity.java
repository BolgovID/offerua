package org.programming.pet.offerua.topic.persistence;

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
@Table(name = "technology_topics")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity extends Auditable<String> implements Serializable {
    @Id
    @UuidGenerator
    UUID id;

    @Column(name = "technology_topic", unique = true, nullable = false)
    String name;

    @Column(name = "technology_id")
    UUID technologyId;
}
