package com.quan.coursepeerreview.Service;

import java.util.List;

import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Request.QuestionRequest;

public interface QuestionService {
    void updateQuestion(Long id, QuestionRequest questionRequest);
    List<Question> getQuestions();
    Question getQuestion(Long id);
    void deleteQuestion(Long id);
}
