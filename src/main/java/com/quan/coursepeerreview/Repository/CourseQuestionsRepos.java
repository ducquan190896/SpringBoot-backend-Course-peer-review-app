package com.quan.coursepeerreview.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.CourseQuestion;

@Repository
public interface CourseQuestionsRepos extends JpaRepository<CourseQuestion, Long> {
    
}
