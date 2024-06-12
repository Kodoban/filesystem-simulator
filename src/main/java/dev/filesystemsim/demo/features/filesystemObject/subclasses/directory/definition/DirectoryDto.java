package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import dev.filesystemsim.demo.features.filesystemObject.FilesystemObjectDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileDto;
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
public class DirectoryDto extends FilesystemObjectDto {

    @JsonIncludeProperties({ "id", "name", "type" })
    private List<FileDto> files;
    
}
