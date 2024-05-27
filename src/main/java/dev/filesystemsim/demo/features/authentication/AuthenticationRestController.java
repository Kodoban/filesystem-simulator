package dev.filesystemsim.demo.features.authentication;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.filesystemsim.demo.controllerMappings.ControllerMapping;
import dev.filesystemsim.demo.features.authentication.service.AuthenticationService;
import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.features.user.definition.UserDtoAfterLogin;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.features.user.mapper.UserMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerMapping.AUTH_REST_CONTROLLER_URL)
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationRestController(AuthenticationService authenticationService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        UserEntity savedUserEntity = authenticationService.register(userEntity);
        return new ResponseEntity<>(
            userMapper.mapEntityToDto(savedUserEntity),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserDtoAfterLogin> loginUser(@Valid @RequestBody UserDto userDto) {

        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        Optional<UserEntity> retrievedUserEntity = authenticationService.findByUsername(userEntity);
        try {
            String jwtToken = authenticationService.login(userEntity);
            UserDto retrievedUserDto = userMapper.mapEntityToDto(retrievedUserEntity.get());
            UserDtoAfterLogin loginResponseDto = new UserDtoAfterLogin(retrievedUserDto, jwtToken); 
            return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}