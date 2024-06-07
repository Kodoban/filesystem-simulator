package dev.filesystemsim.demo.features.directory.service;

import java.util.Optional;

import dev.filesystemsim.demo.features.directory.definition.DirectoryDto;

public interface DirectoryService {

    DirectoryDto save(DirectoryDto directoryDto);

    Optional<DirectoryDto> getDirectoryById(Integer id);

    DirectoryDto update(Integer id, DirectoryDto directoryDto);

    void delete(Integer id);
}
