package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true) //TODO: Check if Override required
public class HomeDirectoryDto extends DirectoryDto {

    @JsonAlias("filesystem")
    @JsonIncludeProperties({ "uuid", "name" })
    private FilesystemDto parentFilesystem;
}
