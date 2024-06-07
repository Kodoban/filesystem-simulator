package dev.filesystemsim.demo.features.filesystemObject.subclasses.file.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileEntity;
import dev.filesystemsim.demo.interfaces.Mapper;

@Component
public class FileMapper implements Mapper<FileEntity, FileDto>{

    private ModelMapper modelMapper;

    public FileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FileEntity mapDtoToEntity(FileDto FileDto) {
        return modelMapper.map(FileDto, FileEntity.class);
    }

    @Override
    public FileDto mapEntityToDto(FileEntity FileEntity) {
        return modelMapper.map(FileEntity, FileDto.class);
    }

}
