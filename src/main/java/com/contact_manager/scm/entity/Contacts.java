package com.contact_manager.scm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contacts")
public class Contacts {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", unique = true, nullable = false)
    private String phoneNo;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "favourite")
    private boolean favourite = false;

    private String instagram;

    private String linkedin;

    @ManyToOne
    private Users user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SocialLinks> socialLinks = new ArrayList<>();
}
