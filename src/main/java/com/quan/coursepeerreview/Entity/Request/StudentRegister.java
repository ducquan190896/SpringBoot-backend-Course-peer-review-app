package com.quan.coursepeerreview.Entity.Request;

import com.quan.coursepeerreview.Entity.Role;

public class StudentRegister {
    private String studentnumber;
    private String lastname;
    private String firstname;
    private String faculty;
    private String group;
    private String username;
    private String password;
    private String confirmedPassword;
    private Role role = Role.USER;
    
    public StudentRegister(String studentnumber, String lastname, String firstname, String faculty, String group,
            String username, String password, String confirmedPassword) {
        this.studentnumber = studentnumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.faculty = faculty;
        this.group = group;
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    
}
