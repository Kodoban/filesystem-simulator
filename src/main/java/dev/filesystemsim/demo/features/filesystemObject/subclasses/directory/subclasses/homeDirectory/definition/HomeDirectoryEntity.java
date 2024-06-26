package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @SuperBuilder
@Entity
@Table(name="home_directory")
@EqualsAndHashCode(callSuper=true) //TODO: Check if Override required
public class HomeDirectoryEntity extends DirectoryEntity {

    @OneToOne(mappedBy = "homeDirectory", cascade = CascadeType.ALL)
    private FilesystemEntity parentFilesystem;

}
