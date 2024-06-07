package dev.filesystemsim.demo.features.homeDirectory;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.filesystemsim.demo.features.homeDirectory.definition.HomeDirectoryDto;
import dev.filesystemsim.demo.features.homeDirectory.service.HomeDirectoryService;
import jakarta.validation.Valid;

public class HomeDirectoryRestController {
    private final HomeDirectoryService homeDirectoryService;

    public HomeDirectoryRestController(HomeDirectoryService homeDirectoryService) {
        this.homeDirectoryService = homeDirectoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeDirectoryDto> getHomeDirectoryById(@PathVariable("id") Integer id) {

        Optional<HomeDirectoryDto> homeDirectoryDto = homeDirectoryService.getHomeDirectoryById(id);
        return homeDirectoryDto.map(homeDirectory -> new ResponseEntity<>(homeDirectory, HttpStatus.OK)).
            orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<HomeDirectoryDto> createHomeDirectory(@Valid @RequestBody HomeDirectoryDto homeDirectoryDto) {
        try {
            HomeDirectoryDto savedHomeDirectoryDto = homeDirectoryService.save(homeDirectoryDto);
            return new ResponseEntity<>(
                savedHomeDirectoryDto,
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
    public ResponseEntity<HomeDirectoryDto> updateHomeDirectoryInfo(
            @PathVariable("id") Integer id,
            @Valid @RequestBody HomeDirectoryDto homeDirectoryDto) {

        try {
            HomeDirectoryDto updatedHomeDirectoryDto = homeDirectoryService.update(id, homeDirectoryDto);
            return new ResponseEntity<>(
                updatedHomeDirectoryDto,
                HttpStatus.OK
        );   
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHomeDirectory(@PathVariable("id") Integer id) {
        homeDirectoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
