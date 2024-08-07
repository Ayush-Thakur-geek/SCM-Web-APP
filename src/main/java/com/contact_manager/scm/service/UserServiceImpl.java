package com.contact_manager.scm.service;

import com.contact_manager.scm.entity.Users;
import com.contact_manager.scm.exceptionHandling.ResourceNotFoundException;
import com.contact_manager.scm.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(Users user) {
        log.info("Saving user: {}", user);
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("User saved successfully.");
        return userRepo.save(user);
    }

    @Override
    public Optional<Users> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<Users> updateUser(Users user) {
        log.info("Updating user: {}", user);
        Users updatedUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No such user found."));
        updatedUser.builder()
                .name(user.getName())
                .email(user.getEmail())
                .contacts(user.getContacts())
                .password(user.getPassword())
                .about(user.getAbout())
                .profilePic(user.getProfilePic())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .phoneVerified(user.isPhoneVerified())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .build();
        userRepo.save(updatedUser);
        log.info("User updated successfully.");
        return Optional.ofNullable(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        log.info("Deleting user with id: {}", id);
        Users user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such user found."));
        userRepo.delete(user);
        log.info("User deleted successfully.");
    }

    @Override
    public boolean isUserExist(String id) {
        Users user = userRepo.findById(id)
                .orElse(null);

        return user != null;
    }

    @Override
    public boolean isUserExistByEmail(String emailId) {
        Users user = userRepo.findByEmail(emailId)
                .orElse(null);

        return user != null;
    }

    @Override
    public List<Users> getAllUsers() {
        return List.of();
    }

    @Override
    public Users getUserByEmail(String email) {
        Users user = userRepo.findByEmail(email)
                .orElse(null);
        return user;
    }
}
