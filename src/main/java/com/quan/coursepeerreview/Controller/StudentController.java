package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.Student;
import com.quan.coursepeerreview.Service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<List<Student>>(studentService.getStudents(), HttpStatus.OK);
    }
    @GetMapping("/allByFaculty/{faculty}")
    public ResponseEntity<List<Student>> getAllByFaculty(@PathVariable String faculty) {
        return new ResponseEntity<List<Student>>(studentService.getStudentsByFaculty(faculty), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
    }
    @GetMapping("/studentnumber/{studentnumber}")
    public ResponseEntity<Student> getStudentByStudentnumber(@PathVariable String studentnumber) {
        return new ResponseEntity<Student>(studentService.getStudentByStudentnumber(studentnumber), HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student, @PathVariable Long id) {
        return new ResponseEntity<Student>(studentService.updateStudent(id, student), HttpStatus.OK);
    }
}
