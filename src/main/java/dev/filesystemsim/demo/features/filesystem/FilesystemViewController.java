package dev.filesystemsim.demo.features.filesystem;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.DirectoryRestController;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.definition.DirectoryDto;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.FileRestController;
import dev.filesystemsim.demo.features.filesystemObject.subclasses.file.definition.FileDto;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping(UrlMapping.FILESYSTEM_CONTROLLER_URL)
public class FilesystemViewController {

    private final FilesystemRestController filesystemRestController;
    private final DirectoryRestController directoryRestController;
    private final FileRestController fileRestController;

    public FilesystemViewController(FilesystemRestController filesystemRestController, 
        DirectoryRestController directoryRestController, FileRestController fileRestController) {
            this.filesystemRestController = filesystemRestController;
            this.directoryRestController = directoryRestController;
            this.fileRestController = fileRestController;
    }

    @GetMapping("/{uuid}")
    public String getFilesystem(@PathVariable("uuid") UUID uuid, Model model) {
        ResponseEntity<FilesystemDto> filesystem = filesystemRestController.getFilesystemByUuid(uuid);
        model.addAttribute("filesystem", filesystem.getBody());
        model.addAttribute("directory", filesystem.getBody().getHomeDirectory());
        return "filesystem";
    }

    @GetMapping("/popup-create-file") 
    public String getCreatePopup() {
        return "fragments/filesystem-directory-contents :: fileCreatePopupFragment";
    }

    // @GetMapping("/popup-delete-file/{id}") 
    // public String getDeleteFilePopup(@PathVariable("id") Integer id, Model model) {
    //     ResponseEntity<FileDto> file = fileRestController.getFileById(id);
    //     model.addAttribute("file", file.getBody());

    //     return "fragments/filesystem-directory-contents :: fileDeletePopupFragment";
    // }

    // @GetMapping("/popup-delete-dir/{id}") 
    // public String getDeleteDirectoryPopup(@PathVariable("id") Integer id, Model model) {
    //     ResponseEntity<DirectoryDto> directory = directoryRestController.getDirectoryById(id);
    //     model.addAttribute("file", directory.getBody());

    //     return "fragments/filesystem-directory-contents :: fileDeletePopupFragment";
    // }

    @PostMapping("/file")
    public String createFile(@Valid @RequestBody FileDto fileDto, Model model) {

        try {
            ResponseEntity<FileDto> file = fileRestController.createFile(fileDto);
            model.addAttribute("file", file.getBody());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO: add attribute Type to FilesystemObject (maps to enum?)
        return "fragments/filesystem-directory-contents :: dirListFileEntry"; 
    }

    @PostMapping("/directory")
    public String createDirectory(@Valid @RequestBody DirectoryDto directoryDto, Model model) {

        try {
            ResponseEntity<DirectoryDto> directory = directoryRestController.createDirectory(directoryDto);
            // model.addAttribute("directory", directory.getBody());
            model.addAttribute("file", directory.getBody());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "fragments/filesystem-directory-contents :: dirListFileEntry"; 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilesystemObject(@PathVariable("id") Integer id) {
        
        ResponseEntity<HttpStatus> deletedObjectStatus = fileRestController.deleteFile(id);
        if (deletedObjectStatus.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Item deleted successfully"); 
        }

        return ResponseEntity
                .status(deletedObjectStatus.getStatusCode())
                .body(deletedObjectStatus.getStatusCode().toString());
    }

    // @GetMapping("/{id}")
    // public String getDeletePopup(@PathVariable("id") Integer id, Model model) {
    //     ResponseEntity<DirectoryDto> directory = directoryRestController.getDirectoryById(id);

    //     model.addAttribute("directory", directory.getBody());
    //     return "fragments/filesystem-directory-contents :: listDirectoryContents";
    // }
    
}
