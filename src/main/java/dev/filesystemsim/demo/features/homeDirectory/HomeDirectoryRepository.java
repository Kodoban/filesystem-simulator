package dev.filesystemsim.demo.features.homeDirectory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.homeDirectory.definition.HomeDirectoryEntity;

@Repository
public interface HomeDirectoryRepository extends CrudRepository<HomeDirectoryEntity, Integer> {

}
