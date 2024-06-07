package dev.filesystemsim.demo.features.directory;

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

import dev.filesystemsim.demo.features.directory.definition.DirectoryDto;
import dev.filesystemsim.demo.features.directory.service.DirectoryService;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping(UrlMapping.DIRECTORY_REST_CONTROLLER_URL)
public class DirectoryRestController {

    private final DirectoryService directoryService;

    public DirectoryRestController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectoryDto> getDirectoryById(@PathVariable("id") Integer id) {

        Optional<DirectoryDto> directoryDto = directoryService.getDirectoryById(id);
        return directoryDto.map(file -> new ResponseEntity<>(file, HttpStatus.OK)).
            orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DirectoryDto> createDirectory(@Valid @RequestBody DirectoryDto directoryDto) {
        try {
            DirectoryDto savedDirectoryDto = directoryService.save(directoryDto);
            return new ResponseEntity<>(
                savedDirectoryDto,
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
    public ResponseEntity<DirectoryDto> updateFileInfo(
            @PathVariable("id") Integer id,
            @Valid @RequestBody DirectoryDto directoryDto) {

        try {
            DirectoryDto updatedDirectoryDto = directoryService.update(id, directoryDto);
            return new ResponseEntity<>(
                updatedDirectoryDto,
                HttpStatus.OK
        );   
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFilesystem(@PathVariable("id") Integer id) {
        directoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
