package com.contact_manager.scm.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be of 10 digits")
    private String phoneNo;
    @NotBlank(message = "Address is required")
    private String address;
    private String description;

    private boolean favourite;
//    @Pattern(regexp = "^(http|https)://(www.)?instagram.com/[a-zA-Z0-9_.]+$", message = "Invalid Instagram link")
    private String instaLink;
//    @Pattern(regexp = "^(http|https)://(www.)?linkedin.com/[a-zA-Z0-9_.]+$", message = "Invalid LinkedIn link" )
    private String linkedinLink;

    private MultipartFile contactImg;
}
