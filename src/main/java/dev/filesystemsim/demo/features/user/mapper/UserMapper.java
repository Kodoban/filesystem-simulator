package dev.filesystemsim.demo.features.user.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.interfaces.Mapper;

@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity mapDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    @Override
    public UserDto mapEntityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

}
