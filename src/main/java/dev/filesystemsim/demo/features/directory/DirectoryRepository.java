package dev.filesystemsim.demo.features.directory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.directory.definition.DirectoryEntity;

@Repository
public interface DirectoryRepository extends CrudRepository<DirectoryEntity, Integer> {

}
