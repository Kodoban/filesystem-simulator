package dev.filesystemsim.demo.features.filesystem.definition;

import java.util.UUID;

import dev.filesystemsim.demo.features.homeDirectory.definition.HomeDirectoryEntity;
import dev.filesystemsim.demo.features.user.definition.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilesystemDto {

    private UUID uuid;
    private String name;
    private UserDto owner;
    private HomeDirectoryEntity homeDirectory;
    
}
