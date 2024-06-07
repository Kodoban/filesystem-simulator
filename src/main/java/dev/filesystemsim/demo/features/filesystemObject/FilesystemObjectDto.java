package dev.filesystemsim.demo.features.filesystemObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class FilesystemObjectDto {

    private Integer id;
    private String name;

    @JsonAlias({"parent"})
    @JsonIncludeProperties({ "id" })
    private DirectoryDto parentDirectory;

}
