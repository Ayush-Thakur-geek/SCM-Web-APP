package com.contact_manager.scm.service;

import com.contact_manager.scm.entity.Contacts;

import java.util.List;

public interface ContactService {
    Contacts saveContact(Contacts contact);

    Contacts update(Contacts contact);

    List<Contacts> getAll();

    Contacts getContactById(String id);

    void deleteContact(String id);

    List<Contacts> search(String name, String email, String pNo);

    List<Contacts> getByUserId(String userId);
}
