package dev.filesystemsim.demo.features.user.service;

import java.util.List;
import java.util.Optional;

import dev.filesystemsim.demo.features.user.definition.UserEntity;

public interface UserService {

    UserEntity save(UserEntity user);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUser(Integer id);

    boolean isExists(Integer id);

    boolean usernameTaken(Integer id, String username);

    UserEntity update(Integer id, UserEntity userEntity);

    void delete(Integer id);
    
}
