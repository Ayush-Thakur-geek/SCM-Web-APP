package com.contact_manager.scm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_details") // Ensure this table name matches your database schema
public class Users implements UserDetails {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", unique = true)
    private String phoneNo;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "about", length = 10000, columnDefinition = "TEXT")
    private String about;

    @Column(name = "profile_pic", length = 10000)
    private String profilePic;

    @Column(name = "enabled")
    @Getter(AccessLevel.NONE)
    private boolean enabled = false;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "phoneNo_verified")
    private boolean phoneVerified = false;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Contacts> contacts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled = true;
    }
}
