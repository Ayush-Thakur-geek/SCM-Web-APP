package com.contact_manager.scm.service;

import com.contact_manager.scm.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Users saveUser(Users user);
    Optional<Users> getUserById(String id);
    Optional<Users> updateUser(Users user);
    void deleteUser(String id);
    boolean isUserExist(String id);
    boolean isUserExistByEmail(String emailId);
    List<Users> getAllUsers();
    Users getUserByEmail(String email);
}
