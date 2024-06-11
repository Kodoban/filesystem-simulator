package dev.filesystemsim.demo.features.filesystem.definition;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryDto;
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
    
    @JsonAlias("user")
    private UserDto owner;

    @JsonAlias("home")
    @JsonIncludeProperties({ "id" })
    private HomeDirectoryDto homeDirectory;
    
}
