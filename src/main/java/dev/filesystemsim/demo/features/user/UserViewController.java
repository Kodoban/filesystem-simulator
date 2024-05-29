package dev.filesystemsim.demo.features.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.urlMappings.UrlMapping;

@Controller
@RequestMapping(UrlMapping.USER_CONTROLLER_URL)
public class UserViewController {

    private final UserRestController userRestController;

    public UserViewController(UserRestController userRestController) {
        this.userRestController = userRestController;
    }

    // GET

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> users = userRestController.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("userListLink", UrlMapping.USER_REST_CONTROLLER_URL);
        return "base"; 
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {
        ResponseEntity<UserDto> userDto = userRestController.getUserById(id);
        if (userDto.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("user", userDto);
            return "user-details";
        } else {
            return "error";
        }
        
    }

    // // POST
    // @PostMapping
    // public String createUser(UserDto userDto, Model model, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
    //         model.addAttribute("userDto", userDto);
    //         return "signup";
    //     }

    //     ResponseEntity<UserDto> userResponseEntity = userRestController.createUser(userDto); //May return null?
    //     if (userResponseEntity.getStatusCode().is2xxSuccessful()) {
    //         return "redirect:/" + ControllerMapping.USER_CONTROLLER_URL + "/" + userResponseEntity.getBody().getId();
    //     } else {
    //         model.addAttribute("error", "Failed to create user");
    //         return "error";
    //     }
    // }

    // @PostMapping("/login")
    // public String processLogin(@Valid @ModelAttribute("user") UserDto userDto, Model model, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
    //         // model.addAttribute("errors");
    //         return "login";
    //     }
        
    //     ResponseEntity<UserDto> matchingUser = userRestController.getUserByUsername(userDto.getUsername());
    //     if (matchingUser.getStatusCode().is2xxSuccessful()) {
    //         UserDto matchingUserData = matchingUser.getBody();
    //         if (matchingUserData.getPassword().equals(userDto.getPassword())) {
    //             return "redirect:/" + ControllerMapping.USER_CONTROLLER_URL + "/" + matchingUserData.getId();
    //         }

    //         return "error";
            
    //     } else {
    //         model.addAttribute("error", "Failed to create user");
    //         return "error";
    //     }
    // }

    // // PATCH
    // @PatchMapping("/{id}")
    // public String updateUserInfo(@PathVariable("id") Integer id, UserDto userDto, Model model, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
    //         model.addAttribute("userDto", userDto);
    //         return "profile";
    //     }

    //     ResponseEntity<UserDto> userResponseEntity = userRestController.createUser(userDto); //May return null?
    //     if (userResponseEntity.getStatusCode().is2xxSuccessful()) {
    //         model.addAttribute("user", userResponseEntity.getBody());
    //         return "redirect:/" + ControllerMapping.USER_CONTROLLER_URL + "/" + userResponseEntity.getBody().getId();
    //     }
    //     else {
    //         model.addAttribute("error", "Failed to update user info");
    //         return "error";
    //     }
    // }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        ResponseEntity<HttpStatus> response = userRestController.deleteUser(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "signup";
        }
        else {
            model.addAttribute("error", "Failed to update user info");
            return "error";
        }
    }
}
