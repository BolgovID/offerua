package org.programming.pet.offerua.users.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.users.domain.UserStatus;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @UuidGenerator
    UUID id;
    String username;
    @JsonIgnore
    String password;
    @Column(name = "first_name")
    String firstName;
    String surname;
    @Column(unique = true)
    String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    UserStatus userStatus = UserStatus.NOT_CONFIRMED;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<UserRole> roles = new HashSet<>();
}
