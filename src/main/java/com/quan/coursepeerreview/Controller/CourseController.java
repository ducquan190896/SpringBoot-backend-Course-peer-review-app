package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseStatus;
import com.quan.coursepeerreview.Service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }
    @GetMapping("/courseStatus/{status}")
    public ResponseEntity<List<Course>> getCoursesByStatus(@PathVariable CourseStatus status) {
        return new ResponseEntity<>(courseService.getCourseByStatus(status), HttpStatus.OK);
    }

   @GetMapping("/id/{id}")
   public ResponseEntity<Course> getCourse(@PathVariable Long id) {
    return new ResponseEntity<Course>(courseService.getCourse(id), HttpStatus.OK);
   }

   @GetMapping("/coursecode/{coursecode}")
   public ResponseEntity<Course> getCourseBycourseCode(@PathVariable String coursecode) {
    return new ResponseEntity<Course>(courseService.getCourseByCode(coursecode), HttpStatus.OK);
   }

   @PostMapping("/")
   public ResponseEntity<HttpStatus> createCourse(@Valid @RequestBody Course course) {
    courseService.saveCourse(course);
    return new ResponseEntity<>(HttpStatus.CREATED);
   } 

   @PutMapping("/id/{id}")
   public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course)  {
    return new ResponseEntity<Course>(courseService.updateCourse(id, course), HttpStatus.OK);
   }
   @PutMapping("/enroll/{courseId}/student/{studentId}")
   public ResponseEntity<HttpStatus> enrollCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
    courseService.enrollCourseByStudent(courseId, studentId);
    return new ResponseEntity<>(HttpStatus.OK);
   }
   @PutMapping("/removeEnrolment/{courseId}/student/{studentId}")
   public ResponseEntity<HttpStatus> RemoveEnrolment(@PathVariable Long courseId, @PathVariable Long studentId) {
    courseService.removeCourseFromStudent(courseId, studentId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @GetMapping("/student/{id}")
   public ResponseEntity<List<Course>> getCourseByStudent(@PathVariable Long id) {
    return new ResponseEntity<List<Course>>(courseService.getCourseByStudent(id), HttpStatus.OK);
   }

}
