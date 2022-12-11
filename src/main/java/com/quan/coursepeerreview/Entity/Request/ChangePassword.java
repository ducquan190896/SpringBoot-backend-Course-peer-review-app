package com.quan.coursepeerreview.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
    private String currentPassword;
    private String password;
    private String confirmedPassword;
}
