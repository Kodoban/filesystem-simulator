package dev.filesystemsim.demo.features.homeDirectory.definition;

import dev.filesystemsim.demo.features.directory.definition.DirectoryDto;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
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

    private FilesystemDto parentFilesystem;
}
