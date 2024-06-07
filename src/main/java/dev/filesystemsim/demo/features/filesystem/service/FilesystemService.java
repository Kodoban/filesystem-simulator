package dev.filesystemsim.demo.features.filesystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;
import dev.filesystemsim.demo.features.user.definition.UserDto;

public interface FilesystemService {

    // FilesystemEntity save(UserDto userDto) throws Exception;

    FilesystemEntity save(FilesystemDto filesystemDto) throws Exception;

    List<FilesystemDto> getAllFilesystems();

    List<FilesystemDto> getAllFilesystems(Integer ownerId);

    Optional<FilesystemDto> getFilesystemByUuid(UUID uuid);

    FilesystemEntity update(UUID uuid, FilesystemEntity filesystemEntity);

    void delete(UUID uuid);
    
}
