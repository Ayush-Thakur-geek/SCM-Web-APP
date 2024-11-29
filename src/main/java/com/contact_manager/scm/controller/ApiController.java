package com.contact_manager.scm.controller;

import com.contact_manager.scm.entity.Contacts;
import com.contact_manager.scm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contacts> getContact(@PathVariable String id) {
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }
}
