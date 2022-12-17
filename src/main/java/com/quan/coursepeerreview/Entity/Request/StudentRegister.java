package com.quan.coursepeerreview.Entity.Request;

import com.quan.coursepeerreview.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegister {
    private String studentnumber;
    private String lastname;
    private String firstname;
    private String faculty;
   
    private String username;
    private String password;
    private String confirmedPassword;
    private Role role = Role.USER;
    
    public StudentRegister(String studentnumber, String lastname, String firstname, String faculty, 
            String username, String password, String confirmedPassword) {
        this.studentnumber = studentnumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.faculty = faculty;
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    
}
