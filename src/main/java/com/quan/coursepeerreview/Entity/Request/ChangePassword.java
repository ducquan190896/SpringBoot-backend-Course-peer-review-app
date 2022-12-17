package com.quan.coursepeerreview.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePassword {
    private String username;
    private String currentPassword;
    private String password;
    private String confirmedPassword;
}
