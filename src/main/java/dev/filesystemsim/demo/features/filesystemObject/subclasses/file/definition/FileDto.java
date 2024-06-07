package dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition;

import dev.filesystemsim.demo.features.filesystemObject.FilesystemObjectDto;
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
public class FileDto extends FilesystemObjectDto {

    private String content;

}
