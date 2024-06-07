package dev.filesystemsim.demo.features.filesystemObject.subclasses.file;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileEntity;

@Repository
public interface FileRepository extends CrudRepository<FileEntity, Integer> {

}
