package com.quan.coursepeerreview.Service;

import java.util.List;

import com.quan.coursepeerreview.Entity.Student;

public interface StudentService {
    Student getStudentById(Long id);
    Student getStudentByStudentnumber(String studentnumber);
    List<Student> getStudents();
    List<Student> getStudentsByFaculty(String faculty);
    void deleteStudent(Long id);
    Student updateStudent(Long id, Student student);
}
