package dev.filesystemsim.demo.features.user.definition;

import java.util.List;

import dev.filesystemsim.demo.features.filesystem.Filesystem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="user")
public class UserEntity {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO: Switch to .IDENTITY, maybe fixes +50 error
    private Integer id;

    @Column(unique=true)
    @NotBlank
    private String username;

    @NotBlank
    private String passwordHash;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Filesystem> systems;

}
