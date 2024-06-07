package dev.filesystemsim.demo.features.filesystem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;
import dev.filesystemsim.demo.features.filesystem.mapper.FilesystemMapper;
import dev.filesystemsim.demo.features.filesystem.service.FilesystemService;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping(UrlMapping.FILESYSTEM_REST_CONTROLLER_URL)
public class FilesystemRestController {

    private final FilesystemService filesystemService;
    private final FilesystemMapper filesystemMapper;

    public FilesystemRestController(FilesystemService filesystemService, FilesystemMapper filesystemMapper) {
        this.filesystemService = filesystemService;
        this.filesystemMapper = filesystemMapper;
    }

    @GetMapping
    public List<FilesystemDto> getAllFilesystems() {
        return filesystemService.getAllFilesystems();
    }

    @GetMapping("/owner/{id}")
    public List<FilesystemDto> getAllFilesystemsByUser(@PathVariable("id") Integer ownerId) {
        return filesystemService.getAllFilesystems(ownerId);
    }

    @GetMapping("/{uuid}") //TODO: Change GET mappings later
    public ResponseEntity<FilesystemDto> getFilesystemByUuid(@PathVariable("uuid") UUID uuid) {

        Optional<FilesystemDto> filesystemDto = filesystemService.getFilesystemByUuid(uuid);
        return filesystemDto.map(filesystem -> new ResponseEntity<>(filesystem, HttpStatus.OK)).
            orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // @PostMapping
    // public ResponseEntity<FilesystemDto> createFilesystem(@Valid @RequestBody UserDto userDto) throws Exception {
    //     try {
    //         FilesystemEntity savedFilesystemEntity = filesystemService.save(userDto);
    //         return new ResponseEntity<>(
    //             filesystemMapper.mapEntityToDto(savedFilesystemEntity),
    //             HttpStatus.CREATED
    //         );
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(
    //             HttpStatus.BAD_REQUEST
    //         );
    //     }
        
    // }

    @PostMapping
    public ResponseEntity<FilesystemDto> createFilesystem(@Valid @RequestBody FilesystemDto filesystemDto) throws Exception {
        try {
            FilesystemEntity savedFilesystemEntity = filesystemService.save(filesystemDto);
            return new ResponseEntity<>(
                filesystemMapper.mapEntityToDto(savedFilesystemEntity),
                HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                HttpStatus.BAD_REQUEST
            );
        }
        
    }

    // Updates system name
    @PatchMapping("/{uuid}")
    public ResponseEntity<FilesystemDto> updateUserInfo(
            @PathVariable("uuid") UUID uuid,
            @Valid @RequestBody FilesystemDto filesystemDto) {

        try {
            FilesystemEntity filesystemEntity = filesystemMapper.mapDtoToEntity(filesystemDto);
            FilesystemEntity updatedFilesystemEntity = filesystemService.update(uuid, filesystemEntity);
            return new ResponseEntity<>(
                filesystemMapper.mapEntityToDto(updatedFilesystemEntity),
                HttpStatus.OK
        );   
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteFilesystem(@PathVariable("uuid") UUID uuid) {
        // TODO: Cascade, if a filesystem is deleted its home directory is deleted too (cascade should also delete the rest of the directories and files etc)
        filesystemService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
