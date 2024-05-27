package dev.filesystemsim.demo.features.user.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true) 
public class UserDtoAfterLogin extends UserDto {

    private String token;

    public UserDtoAfterLogin(UserDto userDto, String token) {
        super(userDto.getId(), userDto.getUsername(), userDto.getPassword());
        this.token = token;
    }

    
}
