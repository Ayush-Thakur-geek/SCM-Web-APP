package com.contact_manager.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Username is required")
    @Size(message = "Min 3 Characters is required", min = 3)
    private String name;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid phone number. It must start with 6-9 and have 10 digits")
    private String pNo;
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank
    @Size(min = 6, message = "Min 3 Characters is required")
    private String password;
    @NotBlank
    private String about;
}
