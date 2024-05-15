package dev.filesystemsim.demo.features.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.filesystemsim.demo.controllerMappings.ControllerMapping;
import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.features.user.mapper.UserMapper;
import dev.filesystemsim.demo.features.user.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerMapping.USER_REST_CONTROLLER_URL)
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // GET

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return users.stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    // @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        Optional<UserEntity> user = userService.getUser(id);
        return user.map(userEntity -> {
                UserDto userDto = userMapper.mapEntityToDto(userEntity);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        UserEntity savedUserEntity = userService.save(userEntity);
        return new ResponseEntity<>(
            userMapper.mapEntityToDto(savedUserEntity),
            HttpStatus.CREATED
        );
    }

    // PATCH

    
    /*
     * Updates username and/or password 
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserInfo(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UserDto userDto) {

        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        // If a new username is passed, it has to not be empty or blank AND not exist in the database
        // In case the username is not passed, the statement stops on the first check
        // TODO: Verify null/blank check on client side
        else if (userService.usernameTaken(id, userDto.getUsername())
            ||(userDto.getUsername()!=null && userDto.getUsername().isBlank())
        ) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        // Assumes values of passed fields are not empty, TODO: check with client code before making request
        UserEntity updatedUserEntity = userService.update(id, userEntity);

        return new ResponseEntity<>(
            userMapper.mapEntityToDto(updatedUserEntity),
            HttpStatus.OK
        );   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
