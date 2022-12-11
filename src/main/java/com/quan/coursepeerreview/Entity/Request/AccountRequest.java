package com.quan.coursepeerreview.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private String username;
    private String password;
    private String confirmedPassword;
}
