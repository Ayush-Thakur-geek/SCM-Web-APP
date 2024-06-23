package com.contact_manager.scm.forms;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    private String name;
    private String pNo;
    private String email;
    private String password;
    private String about;
}
