package dev.filesystemsim.demo.features.filesystem;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesystemRepository extends CrudRepository<Filesystem, UUID> {

}
