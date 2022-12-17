package com.quan.coursepeerreview.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseQuestion;
import com.quan.coursepeerreview.Entity.Request.CourseQuestionRequest;
import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Repository.CourseQuestionsRepos;
import com.quan.coursepeerreview.Repository.CourseRepos;
import com.quan.coursepeerreview.Service.CourseQuestionService;

@Service
public class CourseQuestionServiceIml implements CourseQuestionService{

    @Autowired
    CourseRepos courseRepos;

    @Autowired
    CourseQuestionsRepos courseQuestionsRepos;

    @Override
    public void deleteCourseQestion(Long id) {
        Optional<CourseQuestion> entity = courseQuestionsRepos.findById(id);
        if(!entity.isPresent()) {
         throw new EntityNotFoundException("the courseQuestion with id " + id + " not found");
        }
        courseQuestionsRepos.delete(entity.get());
    }

    @Override
    public List<CourseQuestion> getCourseQuestion() {
        return courseQuestionsRepos.findAll();
    }

    @Override
    public List<CourseQuestion> getCourseQuestionByCourse(Long courseId) {
        
       Optional<Course> entity = courseRepos.findById(courseId);
       if(!entity.isPresent()) {
        throw new EntityNotFoundException("the course with id " + courseId + " not found");
       }
       Course course = entity.get();
       return courseQuestionsRepos.findCourseQuestionByCourse(course);
    }

    @Override
    public void saveCourseQuestion(CourseQuestionRequest courseQuestionRequest) {
        CourseQuestion courseQuestion = new CourseQuestion();
        courseQuestion.setTitle(courseQuestionRequest.getTitle());

        Optional<Course> entity = courseRepos.findById(courseQuestionRequest.getCourseId());
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the course with id " + courseQuestionRequest.getCourseId() + " not found");
        }
        Course course = entity.get();
        courseQuestion.setCourse(course);
        course.getCourseQuestions().add(courseQuestion);
        courseRepos.save(course);
       
    }

    
    
}
