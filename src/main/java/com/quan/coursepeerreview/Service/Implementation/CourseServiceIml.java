package com.quan.coursepeerreview.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseStatus;
import com.quan.coursepeerreview.Entity.Student;
import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Repository.CourseRepos;
import com.quan.coursepeerreview.Repository.StudentRepos;
import com.quan.coursepeerreview.Service.CourseService;

@Service
public class CourseServiceIml implements CourseService {
    @Autowired
    CourseRepos courseRepos;

    @Autowired
    StudentRepos studentRepos;

    @Override
    public void deleteCourse(Long id) {
        Optional<Course> entity = courseRepos.findById(id);
        Course course = isCheck(entity, id);
        courseRepos.delete(course);
        
    }

    @Override
    public Course getCourse(Long id) {
        Optional<Course> entity = courseRepos.findById(id);
        return isCheck(entity, id);
    }

    @Override
    public Course getCourseByCode(String coursecode) {
        Optional<Course> entity = courseRepos.findByCoursecode(coursecode);
        return isCheck(entity, 404L);
    }

    @Override
    public List<Course> getCourses() {
       return courseRepos.findAll();
    }

    @Override
    public List<Course> getCourseByStatus(CourseStatus status) {
        return courseRepos.findCourseByStatus(status);
    }

    @Override
    public void saveCourse(Course course) {
       Optional<Course> entity = courseRepos.findByCoursecode(course.getCoursecode());
       if(entity.isPresent()) {
        throw new EntityNotFoundException("the course with coursecode " + course.getCoursecode() + " already exist");
       }
       course.setCreatedby(LocalDateTime.now());
        courseRepos.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Optional<Course> entity = courseRepos.findById(id);
        Course course2 = isCheck(entity, id);
        course2.setName(course.getName());
        course2.setStatus(course.getStatus());
       return courseRepos.save(course2);
    }
    

    @Override
    public void enrollCourseByStudent(Long courseId, Long studentId) {
        Optional<Course> courseEntity = courseRepos.findById(courseId);
        Course course = isCheck(courseEntity, courseId);

        Optional<Student> studentEntity = studentRepos.findById(studentId);
        if(!studentEntity.isPresent()) {
            throw new EntityNotFoundException("the student with id " + studentId + " not found");
        }
        Student student = studentEntity.get();
        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepos.save(course);
        studentRepos.save(student);
        
    }

    @Override
    public List<Course> getCourseByStudent(Long studentId) {
        Optional<Student> studentEntity = studentRepos.findById(studentId);
        if(!studentEntity.isPresent()) {
            throw new EntityNotFoundException("the student with id " + studentId + " not found");
        }
        Student student = studentEntity.get();
        return student.getCourses().stream().collect(Collectors.toList());
        // or return courseRepos.findCourseByStudents(student);
    }

    @Override
    public void removeCourseFromStudent(Long courseId, Long studentId) {
        Optional<Course> courseEntity = courseRepos.findById(courseId);
        Course course = isCheck(courseEntity, courseId);

        Optional<Student> studentEntity = studentRepos.findById(studentId);
        if(!studentEntity.isPresent()) {
            throw new EntityNotFoundException("the student with id " + studentId + " not found");
        }
        Student student = studentEntity.get();
        course.getStudents().remove(student);
        student.getCourses().remove(course);
        courseRepos.save(course);
        studentRepos.save(student);
        
    }

    private Course isCheck(Optional<Course> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the course with id " + id + " not found");
    }
    
}
