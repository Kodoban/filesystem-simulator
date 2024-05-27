package dev.filesystemsim.demo.features.role;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import dev.filesystemsim.demo.features.user.definition.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserEntity> users;

    public Role(String authority) {
        this.authority = authority;
    }
}

