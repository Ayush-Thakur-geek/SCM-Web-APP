package com.contact_manager.scm.repository;

import com.contact_manager.scm.entity.Contacts;
import com.contact_manager.scm.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, String> {

    Page<Contacts> findByUser(Users user, Pageable pageable);

//    List<Contacts> findByUser(Users user);

    @Query("SELECT c FROM Contacts c WHERE c.user.id = :userId")
    List<Contacts> findByUserId(@Param("userId") String userId);

    Page<Contacts> findByUserAndNameContaining(Users user, String nameKeyWord, Pageable page);
    Page<Contacts> findByUserAndEmailContaining(Users user, String emailKeyWord, Pageable page);
    Page<Contacts> findByUserAndPhoneNoContaining(Users user, String phoneKeyWord, Pageable page);
}
