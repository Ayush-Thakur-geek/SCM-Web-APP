package com.contact_manager.scm.controller;

import com.contact_manager.scm.model.MessageColor;
import com.contact_manager.scm.model.MessageType;
import com.contact_manager.scm.model.Message;
import com.contact_manager.scm.model.Providers;
import com.contact_manager.scm.entity.Users;
import com.contact_manager.scm.forms.UserForm;
import com.contact_manager.scm.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class PageController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        log.info("Inside home handler");
        model.addAttribute("name", "Ayush");
        model.addAttribute("github_link", "https://github.com/Ayush-Thakur-geek");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        log.info("Inside about handler");
        model.addAttribute("isLogin", false);
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage() {
        log.info("Inside services handler");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        log.info("Inside contact handler");
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage() {
        log.info("Inside login handler");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        log.info("Inside register handler");
        UserForm userForm = new UserForm();
        userForm.setPassword("sddsf");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @PostMapping(value = "/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
        log.info("Request posted");
        if (bindingResult.hasErrors()) {
            log.info("Error in form validation:");
            bindingResult.getAllErrors().forEach(error -> {
                log.error("Validation error: {}", error.toString());
            });
            return "register";
        }
        log.info("Validation passed");

        String providerType = Providers.SELF.toString();

        Users user = Users.builder()
                .name(userForm.getName())
                .about(userForm.getAbout())
                .email(userForm.getEmail())
                .phoneNo(userForm.getPNo())
                .password(userForm.getPassword())
                .provider(providerType)
                .profilePic("https://avatars.githubusercontent.com/u/116510022?v=4")
                .build();
        userService.saveUser(user);

        Message message = Message.builder()
                .content("User registered successfully")
                .color(MessageColor.green.toString())
                .type(MessageType.SUCCESS.toString())
                .build();

        session.setAttribute("message", message);
        return "redirect:/register";
    }
}