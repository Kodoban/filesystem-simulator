package dev.filesystemsim.demo.features.directory.definition;

import java.util.List;

import dev.filesystemsim.demo.features.file.definition.FileEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name="directory")
@EqualsAndHashCode(callSuper=true) //TODO: Check if Override required
public class DirectoryEntity extends FileEntity {

    // @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<FileEntity> files;
    
}
