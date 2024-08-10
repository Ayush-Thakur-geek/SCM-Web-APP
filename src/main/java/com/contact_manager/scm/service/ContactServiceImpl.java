package com.contact_manager.scm.service;

import com.contact_manager.scm.entity.Contacts;
import com.contact_manager.scm.entity.Users;
import com.contact_manager.scm.exceptionHandling.ResourceNotFoundException;
import com.contact_manager.scm.repository.ContactRepository;
import com.contact_manager.scm.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactRepository contactRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Contacts saveContact(Contacts contact) {
        log.info("ContactServiceImpl: saveContact");
        contact.setId(UUID.randomUUID().toString());
        log.info("Contact saved");
        return contactRepo.save(contact);
    }

    @Override
    public Contacts update(Contacts contact) {
        return null;
    }

    @Override
    public List<Contacts> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contacts getContactById(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
    }

    @Override
    public void deleteContact(String id) {
        var contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contactRepo.delete(contact);
    }

    @Override
    public Page<Contacts> searchByName(String name, int size, int page, String sortBy, String order, Users user) {
        Sort sort = order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndNameContaining(user, name, pageable);
    }

    @Override
    public Page<Contacts> searchByPhone(String p_no, int size, int page, String sortBy, String order, Users user) {
        Sort sort = order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndPhoneNoContaining(user, p_no, pageable);
    }

    @Override
    public Page<Contacts> searchByEmail(String email, int size, int page, String sortBy, String order, Users user) {
        Sort sort = order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndEmailContaining(user, email, pageable);
    }

    @Override
    public List<Contacts> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public Page<Contacts> getByUser(Users user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUser(user, pageable);
    }
}
