package dev.filesystemsim.demo.features.filesystem.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import dev.filesystemsim.demo.features.filesystem.FilesystemRepository;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;
import dev.filesystemsim.demo.features.filesystem.mapper.FilesystemMapper;
import dev.filesystemsim.demo.features.filesystemObject.Filetype;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryEntity;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.features.user.service.UserService;

@Service
public class FilesystemServiceImpl implements FilesystemService {

    private final FilesystemRepository filesystemRepository;
    private final UserService userService;
    private final FilesystemMapper filesystemMapper;

    public FilesystemServiceImpl(FilesystemRepository filesystemRepository, UserService userService, FilesystemMapper filesystemMapper) {
            this.filesystemRepository = filesystemRepository;
            this.userService = userService;
            this.filesystemMapper = filesystemMapper;
    }

    @Override
    public FilesystemEntity save(FilesystemDto filesystemDto) throws Exception {

        Optional<UserEntity> user = userService.getUser(filesystemDto.getOwner().getId());
        FilesystemEntity filesystemEntity = filesystemMapper.mapDtoToEntity(filesystemDto);
        
        try {
            filesystemEntity.setOwner(user.get());
            filesystemEntity.setHomeDirectory(
                HomeDirectoryEntity.builder()
                    .name("home")
                    .type(Filetype.HOME_DIRECTORY)
                    .build()
            );
            FilesystemEntity savedFilesystemEntity = filesystemRepository.save(filesystemEntity);

            return savedFilesystemEntity;
        } catch (NoSuchElementException e) {
            throw new Exception("User not found");
        }
    }

    @Override
    public List<FilesystemDto> getAllFilesystems() {
        List<FilesystemDto> filesystems = 
                StreamSupport.stream(
                    filesystemRepository
                        .findAll()
                        .spliterator(), 
                        false
                    )
                    .map(filesystemMapper::mapEntityToDto)
                .collect(Collectors.toList());
        
       return filesystems;
    }

    @Override
    public List<FilesystemDto> getAllFilesystems(Integer ownerId) {
        List<FilesystemDto> filesystems = 
                StreamSupport.stream(
                    filesystemRepository
                        .findAllByOwnerId(ownerId)
                        .spliterator(), 
                        false
                    )
                    .map(filesystemMapper::mapEntityToDto)
                .collect(Collectors.toList());
        
       return filesystems;
    }

    @Override
    public Optional<FilesystemDto> getFilesystemByUuid(UUID uuid) {
        return filesystemRepository.findById(uuid)
                .map(filesystemMapper::mapEntityToDto);
    }

    @Override
    public FilesystemEntity update(UUID uuid, FilesystemEntity filesystemEntity) {
        filesystemEntity.setUuid(uuid);
        return filesystemRepository.findById(uuid).map(existingFilesystem -> {
            Optional.ofNullable(filesystemEntity.getName()).ifPresent(existingFilesystem::setName);
            return filesystemRepository.save(existingFilesystem);
        }).orElseThrow(() -> new RuntimeException("Filesystem does not exist"));
    }

    @Override
    public void delete(UUID uuid) {
        filesystemRepository.deleteById(uuid);
    }
    
}
