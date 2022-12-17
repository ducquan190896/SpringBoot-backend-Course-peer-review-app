package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.CourseQuestion;
import com.quan.coursepeerreview.Entity.Request.CourseQuestionRequest;
import com.quan.coursepeerreview.Service.CourseQuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coursequestion")
public class CourseQuestionController {
    @Autowired
    CourseQuestionService courseQuestionService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseQuestion>> getAll() {
        return new ResponseEntity<List<CourseQuestion>>(courseQuestionService.getCourseQuestion(), HttpStatus.OK);
    }
    @GetMapping("/course/{id}")
    public ResponseEntity<List<CourseQuestion>> getAllByCourse(@PathVariable Long id) {
        return new ResponseEntity<List<CourseQuestion>>(courseQuestionService.getCourseQuestionByCourse(id), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteCourseQuestion(@PathVariable Long id) {
        courseQuestionService.deleteCourseQestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveCourse(@Valid @RequestBody CourseQuestionRequest courseQuestionRequest) {
        courseQuestionService.saveCourseQuestion(courseQuestionRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
