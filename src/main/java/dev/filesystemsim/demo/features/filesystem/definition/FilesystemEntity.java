package dev.filesystemsim.demo.features.filesystem.definition;

import java.util.UUID;

import dev.filesystemsim.demo.features.homeDirectory.definition.HomeDirectoryEntity;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class FilesystemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotBlank
    private String name;

    // @Column(nullable = false)
    @NotNull
    @ManyToOne
    // @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    // @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_directory_id", referencedColumnName = "id")
    private HomeDirectoryEntity homeDirectory;
    
}
