package dev.filesystemsim.demo.features.directory.definition;

import java.util.List;

import dev.filesystemsim.demo.features.file.definition.FileDto;
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
public class DirectoryDto extends FileDto {

    private List<FileDto> files;
    
}
