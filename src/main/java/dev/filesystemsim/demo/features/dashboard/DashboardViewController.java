package dev.filesystemsim.demo.features.dashboard;

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

import dev.filesystemsim.demo.features.filesystem.FilesystemRestController;
import dev.filesystemsim.demo.features.filesystem.definition.FilesystemDto;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping(UrlMapping.DASHBOARD_CONTROLLER_URL)
public class DashboardViewController {

    private final FilesystemRestController filesystemRestController;

    public DashboardViewController(FilesystemRestController filesystemRestController) {
        this.filesystemRestController = filesystemRestController;
    }

    @GetMapping
    public String getDashboard(Model model) {
        return "dashboard"; 
    }

    @GetMapping("popup-create")
    public String getCreatePopup() {
        return "fragments/filesystem-fragments :: filesystemCreatePopupFragment";
    }

    @GetMapping("popup-delete/{uuid}")
    public String getDeletePopup(@PathVariable("uuid") UUID uuid, Model model) {
        ResponseEntity<FilesystemDto> filesystem = filesystemRestController.getFilesystemByUuid(uuid);

        model.addAttribute("filesystem", filesystem.getBody());
        return "fragments/filesystem-fragments :: filesystemDeletePopupFragment";
    }

    @PostMapping
    public String createFilesystem(@Valid @RequestBody FilesystemDto filesystemDto, Model model) {
        
        try {
            ResponseEntity<FilesystemDto> filesystem = filesystemRestController.createFilesystem(filesystemDto);
            model.addAttribute("filesystem", filesystem.getBody());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "fragments/filesystem-fragments :: newFilesystemFragment"; 
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteFilesystem(@PathVariable("uuid") UUID uuid) {
        
        ResponseEntity<HttpStatus> deletedFilesystemStatus = filesystemRestController.deleteFilesystem(uuid);
        if (deletedFilesystemStatus.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Filesystem deleted successfully"); 
        }

        return ResponseEntity
                .status(deletedFilesystemStatus.getStatusCode())
                .body(deletedFilesystemStatus.getStatusCode().toString());
    }
}
