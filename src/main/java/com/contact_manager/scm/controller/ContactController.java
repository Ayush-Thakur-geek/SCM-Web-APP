package com.contact_manager.scm.controller;

import com.contact_manager.scm.entity.Contacts;
import com.contact_manager.scm.entity.Users;
import com.contact_manager.scm.forms.ContactForm;
import com.contact_manager.scm.model.Fetch;
import com.contact_manager.scm.model.Message;
import com.contact_manager.scm.model.MessageColor;
import com.contact_manager.scm.model.MessageType;
import com.contact_manager.scm.service.ContactService;
import com.contact_manager.scm.service.ImageService;
import com.contact_manager.scm.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/contact")
@Log4j2
public class ContactController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addContact(Model model) {
        model.addAttribute("contact", new ContactForm());
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute("contact") ContactForm cf, BindingResult result, Authentication authentication, Model model, HttpSession session) {
        System.out.println("ContactForm: " + cf);

        if (result.hasErrors()) {
            model.addAttribute("contact", cf);
            return "user/add_contact";
        }

        String username = Fetch.getEmailOfLoggedInUser(authentication);
        Users user = userService.getUserByEmail(username);

        log.info("file info: {}", cf.getContactImg().getOriginalFilename());
        System.out.println("file info: " + cf.getContactImg().getOriginalFilename());
        String ImageFileName = UUID.randomUUID().toString();
        String fileURL = imageService.uploadImg(cf.getContactImg(), ImageFileName);

        Contacts contact = Contacts.builder()
                .name(cf.getName())
                .email(cf.getEmail())
                .phoneNo(cf.getPhoneNo())
                .address(cf.getAddress())
                .description(cf.getDescription())
                .favourite(cf.isFavourite())
                .instagram(cf.getInstaLink())
                .linkedin(cf.getLinkedinLink())
                .profilePic(fileURL)
                .cloudinaryImagePublicId(ImageFileName)
                .user(user)
                .build();

        Message message = Message.builder()
                .content("Contact saved successfully")
                .color(MessageColor.green.toString())
                .type(MessageType.SUCCESS.toString())
                .build();

        session.setAttribute("message", message);
        contactService.saveContact(contact);
        return "redirect:/user/contact/add";
    }
    @GetMapping("/view")
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication
    ) {
        String username = Fetch.getEmailOfLoggedInUser(authentication);
        Users user = userService.getUserByEmail(username);
        Page<Contacts> pageContacts = contactService.getByUser(user, page, size, sortBy, direction);
        model.addAttribute("pageContacts", pageContacts);
        return "user/contacts";
    }
}

