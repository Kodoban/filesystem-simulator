package dev.filesystemsim.demo.features.authentication.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.filesystemsim.demo.features.role.Role;
import dev.filesystemsim.demo.features.role.RoleRepository;
import dev.filesystemsim.demo.features.user.UserRepository;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.utils.TokenService;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    public AuthenticationServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity register(UserEntity user) {
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        user.setAuthorities(authorities);
        
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByUsername(UserEntity user) {
        return userRepository.findByUsername(user.getUsername());
    }

    @Override 
    public String login(UserEntity user) {

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            return token;
            
        } catch (BadCredentialsException e) {
            return "";
        }
        catch (AuthenticationException e) {
            return "";
        }
    }

}
