package dev.filesystemsim.demo.features.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.user.definition.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);

    boolean existsByIdNotAndUsername(Integer id, String username);

}
