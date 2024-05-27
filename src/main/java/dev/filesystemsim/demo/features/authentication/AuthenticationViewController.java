package dev.filesystemsim.demo.features.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.filesystemsim.demo.controllerMappings.ControllerMapping;
import dev.filesystemsim.demo.features.user.definition.UserDto;
import jakarta.validation.Valid;

@Controller
@RequestMapping(ControllerMapping.AUTH_CONTROLLER_URL)
public class AuthenticationViewController {

    private final AuthenticationRestController authenticationRestController;

    public AuthenticationViewController(AuthenticationRestController authenticationRestController) {
        this.authenticationRestController = authenticationRestController;
    }

    @GetMapping("/login")
    public String getLoginPage(
        @RequestParam(required = false) String foo,
        Model model
    ) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("user") UserDto userDto) {
        authenticationRestController.loginUser(userDto);
        return "redirect:/login?success";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto) {
        authenticationRestController.registerUser(userDto);
        // TODO: Redirect to user profile page
        return "redirect:/auth/login";
        // return "redirect:/" + ControllerMapping.USER_CONTROLLER_URL + "/" + matchingUserData.getId();
    }
}
