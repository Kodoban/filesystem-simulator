package dev.filesystemsim.demo.features.filesystemObject.subclasses.file.service;

import java.util.Optional;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileDto;

public interface FileService {

    FileDto save(FileDto fileDto);

    Optional<FileDto> getFileById(Integer id);

    FileDto update(Integer id, FileDto fileDto);

    void delete(Integer id);

}
