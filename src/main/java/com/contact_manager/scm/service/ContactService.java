package com.contact_manager.scm.service;

import com.contact_manager.scm.entity.Contacts;
import com.contact_manager.scm.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {
    Contacts saveContact(Contacts contact);

    Contacts update(Contacts contact);

    List<Contacts> getAll();

    Contacts getContactById(String id);

    void deleteContact(String id);

    Page<Contacts> searchByName(String name, int size, int page, String sortBy, String order, Users user);

    Page<Contacts> searchByPhone(String p_no, int size, int page, String sortBy, String order, Users user);

    Page<Contacts> searchByEmail(String email, int size, int page, String sortBy, String order, Users user);

    List<Contacts> getByUserId(String userId);

    Page<Contacts> getByUser(Users user, int page, int size, String sortField, String sortDirection);
}
