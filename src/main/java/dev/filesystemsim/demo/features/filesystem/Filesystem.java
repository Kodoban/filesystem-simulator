package dev.filesystemsim.demo.features.filesystem;

import java.util.UUID;

import dev.filesystemsim.demo.features.homeDirectory.HomeDirectory;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="filesystem")
public class Filesystem {

    @Id
    private UUID uuid;

    // @Column(nullable = false)
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    // @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_directory_id", referencedColumnName = "id")
    private HomeDirectory homeDirectory;
    
}
