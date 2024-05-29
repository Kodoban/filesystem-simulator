package dev.filesystemsim.demo.features.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.filesystemsim.demo.urlMappings.UrlMapping;

@Controller
@RequestMapping(UrlMapping.DASHBOARD_CONTROLLER_URL)
public class DashboardViewController {

    @GetMapping
    public String getDashboard(Model model) {
        return "dashboard"; 
    }

    @PostMapping
    public String aDashboard(Model model) {
        return "dashboard"; 
    }
}
