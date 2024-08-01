package org.programming.pet.offerua.technologies.persistence;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.domain.Auditable;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "technologies")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyEntity extends Auditable<String> implements Serializable {
    @Id
    @UuidGenerator
    UUID id;

    @Column(name = "technology_name", unique = true, nullable = false)
    String technologyName;

    @Column(name = "technology_display_name", unique = true)
    String technologyDisplayName;
}