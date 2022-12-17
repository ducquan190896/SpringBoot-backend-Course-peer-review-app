package com.quan.coursepeerreview.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseQuestion;

@Repository
public interface CourseQuestionsRepos extends JpaRepository<CourseQuestion, Long> {
    
    List<CourseQuestion> findCourseQuestionByCourse(Course course);
}
