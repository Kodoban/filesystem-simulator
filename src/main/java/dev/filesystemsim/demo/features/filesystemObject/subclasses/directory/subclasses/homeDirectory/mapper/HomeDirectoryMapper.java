package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryEntity;
import dev.filesystemsim.demo.interfaces.Mapper;

@Component
public class HomeDirectoryMapper implements Mapper<HomeDirectoryEntity, HomeDirectoryDto> {

    private ModelMapper modelMapper;

    public HomeDirectoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HomeDirectoryEntity mapDtoToEntity(HomeDirectoryDto HomeDirectoryDto) {
        return modelMapper.map(HomeDirectoryDto, HomeDirectoryEntity.class);
    }

    @Override
    public HomeDirectoryDto mapEntityToDto(HomeDirectoryEntity HomeDirectoryEntity) {
        return modelMapper.map(HomeDirectoryEntity, HomeDirectoryDto.class);
    }
}
