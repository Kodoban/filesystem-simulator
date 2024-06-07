package dev.filesystemsim.demo.features.file.service;

import java.util.Optional;

import dev.filesystemsim.demo.features.file.definition.FileDto;
import jakarta.validation.Valid;

public interface FileService {

    FileDto save(FileDto fileDto);

    Optional<FileDto> getFileById(Integer id);

    FileDto update(Integer id, FileDto fileDto);

    void delete(Integer id);

}
