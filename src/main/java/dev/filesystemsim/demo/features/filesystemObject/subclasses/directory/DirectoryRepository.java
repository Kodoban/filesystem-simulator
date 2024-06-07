package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryEntity;

@Repository
public interface DirectoryRepository extends CrudRepository<DirectoryEntity, Integer> {

}
