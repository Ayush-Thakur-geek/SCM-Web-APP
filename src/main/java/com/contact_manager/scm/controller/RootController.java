package com.contact_manager.scm.controller;

import com.contact_manager.scm.entity.Users;
import com.contact_manager.scm.model.Fetch;
import com.contact_manager.scm.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@Log4j2
public class RootController {
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInfo(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        log.info("Email of logged in user: {}", Fetch.getEmailOfLoggedInUser(authentication));
        Users user = userService.getUserByEmail(Fetch.getEmailOfLoggedInUser(authentication));

        log.info("User: {}", user);
        model.addAttribute("user", user);
    }

}
