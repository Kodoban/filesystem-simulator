package dev.filesystemsim.demo.features.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

import dev.filesystemsim.demo.features.user.UserRepository;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.features.user.exceptions.UsernameAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username " + user.getUsername() + " already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return StreamSupport.stream(userRepository
                            .findAll()
                            .spliterator(), 
                            false)
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean usernameTaken(Integer id, String username) {
        return userRepository.existsByIdNotAndUsername(id, username);
    }

    @Override
    public UserEntity update(Integer id, UserEntity userEntity) {
        userEntity.setId(id);
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userEntity.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(userEntity.getPasswordHash()).ifPresent(existingUser::setPasswordHash);
            return userRepository.save(existingUser);
            
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    
}
