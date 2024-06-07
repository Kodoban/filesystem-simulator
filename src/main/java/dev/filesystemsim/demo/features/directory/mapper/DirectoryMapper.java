package dev.filesystemsim.demo.features.directory.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.filesystemsim.demo.features.directory.definition.DirectoryDto;
import dev.filesystemsim.demo.features.directory.definition.DirectoryEntity;
import dev.filesystemsim.demo.interfaces.Mapper;

@Component
public class DirectoryMapper implements Mapper<DirectoryEntity, DirectoryDto> {

    private ModelMapper modelMapper;

    public DirectoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DirectoryEntity mapDtoToEntity(DirectoryDto directoryDto) {
        return modelMapper.map(directoryDto, DirectoryEntity.class);
    }

    @Override
    public DirectoryDto mapEntityToDto(DirectoryEntity directoryEntity) {
        return modelMapper.map(directoryEntity, DirectoryDto.class);
    }
}
