package dev.filesystemsim.demo.features.user.definition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    
    private Integer id;
    private String username;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String passwordHash;
    
    
}
