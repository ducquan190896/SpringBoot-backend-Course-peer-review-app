package com.quan.coursepeerreview.Entity;

public enum CourseStatus {
    OPEN("OPEN"),
    CLOSED("CLOSED");

    private String name;

    CourseStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
