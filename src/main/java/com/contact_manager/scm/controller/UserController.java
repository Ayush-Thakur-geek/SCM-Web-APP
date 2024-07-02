package com.contact_manager.scm.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@Log4j2
public class UserController {


    //dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    //profile
    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }
}
