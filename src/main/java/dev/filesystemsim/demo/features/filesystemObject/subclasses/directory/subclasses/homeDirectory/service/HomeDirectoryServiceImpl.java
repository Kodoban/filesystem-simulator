package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.HomeDirectoryRepository;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryEntity;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.mapper.HomeDirectoryMapper;

@Service
public class HomeDirectoryServiceImpl implements HomeDirectoryService {

    private final HomeDirectoryMapper homeDirectoryMapper;
    private final HomeDirectoryRepository homeDirectoryRepository;
    
    public HomeDirectoryServiceImpl(HomeDirectoryMapper homeDirectoryMapper, HomeDirectoryRepository homeDirectoryRepository) {
        this.homeDirectoryMapper = homeDirectoryMapper;
        this.homeDirectoryRepository = homeDirectoryRepository;
    }

    @Override
    public HomeDirectoryDto save(HomeDirectoryDto homeDirectoryDto) {
        HomeDirectoryEntity homeDirectoryEntity = homeDirectoryMapper.mapDtoToEntity(homeDirectoryDto);
        HomeDirectoryEntity savedHomeDirectoryEntity = homeDirectoryRepository.save(homeDirectoryEntity);
        return homeDirectoryMapper.mapEntityToDto(savedHomeDirectoryEntity);
    }

    @Override
    public Optional<HomeDirectoryDto> getHomeDirectoryById(Integer id) {
        return homeDirectoryRepository.findById(id).map(
            homeDirectoryMapper::mapEntityToDto
        );
    }

    @Override
    public HomeDirectoryDto update(Integer id, HomeDirectoryDto directoryDto) {
        HomeDirectoryEntity homeDirectoryEntity = homeDirectoryMapper.mapDtoToEntity(directoryDto);
        HomeDirectoryEntity savedHomeDirectoryEntity = homeDirectoryRepository.findById(id).map(existingHomeDirectory -> {
                Optional.ofNullable(homeDirectoryEntity.getName()).ifPresent(existingHomeDirectory::setName);
                return homeDirectoryRepository.save(existingHomeDirectory);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
        return homeDirectoryMapper.mapEntityToDto(savedHomeDirectoryEntity);
    }

    @Override
    public void delete(Integer id) {
        homeDirectoryRepository.deleteById(id);
    }

}
