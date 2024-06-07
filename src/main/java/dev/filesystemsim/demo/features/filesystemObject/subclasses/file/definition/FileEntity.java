package dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition;

import dev.filesystemsim.demo.features.filesystemObject.FilesystemObjectEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="file")
public class FileEntity extends FilesystemObjectEntity {

    private String content;
}
