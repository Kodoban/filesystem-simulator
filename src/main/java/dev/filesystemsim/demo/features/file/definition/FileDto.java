package dev.filesystemsim.demo.features.file.definition;

import dev.filesystemsim.demo.features.directory.definition.DirectoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FileDto {

    private Integer id;
    private String name;
    private String content;
    // private DirectoryEntity parentDirectory;

}
