package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.filesystemsim.demo.features.filesystemObject.Filetype;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.DirectoryRepository;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryEntity;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.mapper.DirectoryMapper;

@Service
public class DirectoryServiceImpl implements DirectoryService {

    private final DirectoryMapper directoryMapper;
    private final DirectoryRepository directoryRepository;
    
    public DirectoryServiceImpl(DirectoryMapper directoryMapper, DirectoryRepository directoryRepository) {
        this.directoryMapper = directoryMapper;
        this.directoryRepository = directoryRepository;
    }

    @Override
    public DirectoryDto save(DirectoryDto directoryDto) {
        DirectoryEntity directoryEntity = directoryMapper.mapDtoToEntity(directoryDto);
        directoryEntity.setType(Filetype.DIRECTORY);
        DirectoryEntity savedDirectoryEntity = directoryRepository.save(directoryEntity);
        return directoryMapper.mapEntityToDto(savedDirectoryEntity);
    }

    @Override
    public Optional<DirectoryDto> getDirectoryById(Integer id) {
        return directoryRepository.findById(id).map(
            directoryMapper::mapEntityToDto
        );
    }

    @Override
    public DirectoryDto update(Integer id, DirectoryDto directoryDto) {
        DirectoryEntity directoryEntity = directoryMapper.mapDtoToEntity(directoryDto);
        DirectoryEntity savedDirectoryEntity = directoryRepository.findById(id).map(existingDirectory -> {
                Optional.ofNullable(directoryEntity.getName()).ifPresent(existingDirectory::setName);
                return directoryRepository.save(existingDirectory);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
        return directoryMapper.mapEntityToDto(savedDirectoryEntity);
    }

    @Override
    public void delete(Integer id) {
        directoryRepository.deleteById(id);
    }

    

}
