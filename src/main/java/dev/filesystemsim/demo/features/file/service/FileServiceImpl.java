package dev.filesystemsim.demo.features.file.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.filesystemsim.demo.features.file.FileRepository;
import dev.filesystemsim.demo.features.file.definition.FileDto;
import dev.filesystemsim.demo.features.file.definition.FileEntity;
import dev.filesystemsim.demo.features.file.mapper.FileMapper;
import jakarta.validation.Valid;

@Service
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;
    private final FileRepository fileRepository;

    public FileServiceImpl(FileMapper fileMapper, FileRepository fileRepository) {
        this.fileMapper = fileMapper;
        this.fileRepository = fileRepository;
    }

    @Override
    public FileDto save(FileDto fileDto) {
        FileEntity fileEntity = fileMapper.mapDtoToEntity(fileDto);
        FileEntity savedFileEntity = fileRepository.save(fileEntity);
        return fileMapper.mapEntityToDto(savedFileEntity);
    }

    @Override
    public Optional<FileDto> getFileById(Integer id) {
        return fileRepository.findById(id).map(
            fileMapper::mapEntityToDto
        );
    }

    @Override
    public FileDto update(Integer id, FileDto fileDto) {
        FileEntity fileEntity = fileMapper.mapDtoToEntity(fileDto);
        FileEntity savedFileEntity = fileRepository.findById(id).map(existingFile -> {
                Optional.ofNullable(fileEntity.getName()).ifPresent(existingFile::setName);
                Optional.ofNullable(fileEntity.getContent()).ifPresent(existingFile::setContent);
                return fileRepository.save(existingFile);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
        return fileMapper.mapEntityToDto(savedFileEntity);
    }

    @Override
    public void delete(Integer id) {
        fileRepository.deleteById(id);
    }

}
