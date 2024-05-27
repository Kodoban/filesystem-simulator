package dev.filesystemsim.demo.features.authentication.service;

import java.util.Optional;

import dev.filesystemsim.demo.features.user.definition.UserEntity;

public interface AuthenticationService {

    UserEntity register(UserEntity user);

    Optional<UserEntity> findByUsername(UserEntity user);
    
    String login(UserEntity user);

}
