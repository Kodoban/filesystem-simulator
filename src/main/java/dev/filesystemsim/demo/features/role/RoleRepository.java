// AFTER

package dev.filesystemsim.demo.features.role;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
    Optional<Role> findByAuthority(String authority);
}

