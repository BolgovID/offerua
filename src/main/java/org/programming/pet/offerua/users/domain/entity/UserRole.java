package org.programming.pet.offerua.users.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.dto.UserRoleName;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    @Id
    @UuidGenerator
    UUID id;
    @Enumerated(EnumType.STRING)
    UserRoleName name;
}
