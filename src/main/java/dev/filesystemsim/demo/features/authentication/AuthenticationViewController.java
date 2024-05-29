package dev.filesystemsim.demo.features.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.features.user.definition.UserDtoAfterLogin;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping(UrlMapping.AUTH_CONTROLLER_URL)
public class AuthenticationViewController {

    private final AuthenticationRestController authenticationRestController;

    public AuthenticationViewController(AuthenticationRestController authenticationRestController) {
        this.authenticationRestController = authenticationRestController;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("user") UserDto userDto, Model model) {
        ResponseEntity<UserDtoAfterLogin> user = authenticationRestController.loginUser(userDto);
        try {
            model.addAttribute("token", user.getBody().getToken());
            // https://www.reddit.com/r/reactjs/comments/jjkddv/how_to_store_jwt_in_the_client_side/
            // https://tkacz.pro/how-to-securely-store-jwt-tokens/
            // https://medium.com/spring-boot/spring-boot-3-spring-security-6-jwt-authentication-authorization-98702d6313a5
            return "redirect:/dashboard";
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto) {
        authenticationRestController.registerUser(userDto);
        // TODO: Redirect to user profile page
        return "redirect:/auth/login?success";
        // return "redirect:/" + ControllerMapping.USER_CONTROLLER_URL + "/" + matchingUserData.getId();
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

    @PostMapping("/logout")
    public String logoutP() {
        return "index";
    }
}
