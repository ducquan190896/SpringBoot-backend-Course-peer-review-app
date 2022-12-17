package com.quan.coursepeerreview.Service;

import java.util.List;

import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseStatus;

public interface CourseService {
    List<Course> getCourses();
    List<Course> getCourseByStatus(CourseStatus status);
    Course getCourse(Long id);
    Course getCourseByCode(String coursecode);
    void saveCourse(Course course);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    void enrollCourseByStudent(Long courseId, Long studentId);
    void removeCourseFromStudent(Long courseId, Long studentId);
    List<Course> getCourseByStudent(Long studentId);
}
