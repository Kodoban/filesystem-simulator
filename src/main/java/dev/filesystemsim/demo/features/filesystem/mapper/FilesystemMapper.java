package dev.filesystemsim.demo.features.filesystem.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;
import dev.filesystemsim.demo.interfaces.Mapper;

@Component
public class FilesystemMapper implements Mapper<FilesystemEntity, FilesystemDto> {

    private ModelMapper modelMapper;

    public FilesystemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FilesystemEntity mapDtoToEntity(FilesystemDto filesystemDto) {
        return modelMapper.map(filesystemDto, FilesystemEntity.class);
    }

    @Override
    public FilesystemDto mapEntityToDto(FilesystemEntity filesystemEntity) {
        return modelMapper.map(filesystemEntity, FilesystemDto.class);
    }

}
