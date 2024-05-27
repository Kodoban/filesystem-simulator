package dev.filesystemsim.demo.features.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.user.definition.UserEntity;

@Repository
// TODO: Change to JpaRepository?
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);

    boolean existsByIdNotAndUsername(Integer id, String username);

    Optional<UserEntity> findByUsername(String username);

}
