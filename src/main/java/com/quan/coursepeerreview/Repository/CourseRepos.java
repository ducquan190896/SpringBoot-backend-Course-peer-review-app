package com.quan.coursepeerreview.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseStatus;
import com.quan.coursepeerreview.Entity.Student;

@Repository
public interface CourseRepos extends JpaRepository<Course, Long> {
    
    List<Course> findCourseByStatus(CourseStatus status);

    //check this one
    List<Course> findCourseByStudents(Student student);

    @Query(
        value = "select * from course where coursecode = ?",
        nativeQuery = true
    )
    Optional<Course> findByCoursecode(String coursecode);
}
