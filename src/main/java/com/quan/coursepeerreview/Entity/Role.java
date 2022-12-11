package com.quan.coursepeerreview.Entity;

public enum Role {
    USER("ROLE_USER"),
    TEACHER("ROLE_TEACHER"),
    ADMIN("ROLE_ADMIN");

    private String name;

    Role(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    
}
