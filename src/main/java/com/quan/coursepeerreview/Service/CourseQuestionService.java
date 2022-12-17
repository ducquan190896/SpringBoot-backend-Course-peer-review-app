package com.quan.coursepeerreview.Service;

import java.util.List;

import com.quan.coursepeerreview.Entity.CourseQuestion;
import com.quan.coursepeerreview.Entity.Request.CourseQuestionRequest;

public interface CourseQuestionService {
    List<CourseQuestion> getCourseQuestion();
    List<CourseQuestion> getCourseQuestionByCourse(Long courseId);
    void saveCourseQuestion(CourseQuestionRequest courseQuestionRequest);
    void deleteCourseQestion(Long id);
}
