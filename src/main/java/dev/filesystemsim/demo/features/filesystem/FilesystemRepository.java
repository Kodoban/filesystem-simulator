package dev.filesystemsim.demo.features.filesystem;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;

@Repository
public interface FilesystemRepository extends CrudRepository<FilesystemEntity, UUID> {

    List<FilesystemEntity> findAllByOwnerId(Integer ownerId);

}
