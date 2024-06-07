package dev.filesystemsim.demo.features.filesystemObject.subclasses.file;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.service.FileService;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping(UrlMapping.FILE_REST_CONTROLLER_URL)
public class FileRestController {

    private final FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    // @GetMapping("/{uuid}")
    // public List<FileDto> getAllFilesByFilesystem(@PathVariable("uuid") UUID uuid) {
    //     return fileService.getAllFiles(uuid);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getFileById(@PathVariable("id") Integer id) {

        Optional<FileDto> fileDto = fileService.getFileById(id);
        return fileDto.map(file -> new ResponseEntity<>(file, HttpStatus.OK)).
            orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<FileDto> createFile(@Valid @RequestBody FileDto fileDto) {
        try {
            FileDto savedFileDto = fileService.save(fileDto);
            return new ResponseEntity<>(
                savedFileDto,
                HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                HttpStatus.BAD_REQUEST
            );
        }
    }

    // Updates name,content
    @PatchMapping("/{id}")
    public ResponseEntity<FileDto> updateFileInfo(
            @PathVariable("id") Integer id,
            @Valid @RequestBody FileDto fileDto) {

        try {
            FileDto updatedFileDto = fileService.update(id, fileDto);
            return new ResponseEntity<>(
                updatedFileDto,
                HttpStatus.OK
        );   
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFile(@PathVariable("id") Integer id) {
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
