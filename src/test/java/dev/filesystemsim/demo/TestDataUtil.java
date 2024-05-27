package dev.filesystemsim.demo;

import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.features.user.definition.UserEntity;

public final class TestDataUtil {
    
    private TestDataUtil() {}

    public static UserEntity createTestUserEntityA() {
        return UserEntity.builder()
                .username("Sam")
                // .passwordHash("123454")
                .build();
    }

    public static UserDto createTestUserDtoA() {
        return UserDto.builder()
                .username("Sam")
                // .passwordHash("123454")
                .build();
    }

    public static UserEntity createTestUserEntityB() {
        return UserEntity.builder()
                .username("Tom")
                // .passwordHash("afadf")
                .build();
    }
}
