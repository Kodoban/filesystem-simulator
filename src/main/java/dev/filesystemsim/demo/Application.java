package dev.filesystemsim.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.filesystemsim.demo.features.role.Role;
import dev.filesystemsim.demo.features.role.RoleRepository;
import dev.filesystemsim.demo.features.user.UserRepository;
import dev.filesystemsim.demo.features.user.definition.UserEntity;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			UserEntity admin = new UserEntity();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setAuthorities(roles);

			userRepository.save(admin);
		};
	}

}
