package com.quan.coursepeerreview.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseQuestion;
import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Student;
import com.quan.coursepeerreview.Entity.Request.ReviewRequest;
import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Repository.AccountRepos;
import com.quan.coursepeerreview.Repository.CourseRepos;
import com.quan.coursepeerreview.Repository.PeerReviewRepos;
import com.quan.coursepeerreview.Repository.QuestionRepos;
import com.quan.coursepeerreview.Repository.StudentRepos;
import com.quan.coursepeerreview.Service.PeerReviewService;

@Service
public class PeerReviewServiceImp implements PeerReviewService{
    @Autowired
    PeerReviewRepos peerReviewRepos;
    @Autowired
    AccountRepos accountRepos;
    @Autowired
    StudentRepos studentRepos;
    @Autowired
    CourseRepos courseRepos;
  

    @Autowired
    QuestionRepos questionRepos;

    @Override
    public void deleteReview(Long id) {
        Optional<PeerReview> entity = peerReviewRepos.findById(id);
        if(entity.isPresent()) {
            peerReviewRepos.deleteById(id);
        }
        throw new EntityNotFoundException("the review with id " + id + " not found");
    }
    @Override
    public List<PeerReview> getReviews() {
       return peerReviewRepos.findAll();
    }
    @Override
    public List<PeerReview> getReviewsByAccount(Long accountId) {
        Optional<Account> entity = accountRepos.findById(accountId);
        if(entity.isPresent()) {
            return peerReviewRepos.findPeerReviewByAccount(entity.get());
        }
        throw new EntityNotFoundException("the account with id " + accountId + " not found");
    }
    @Override
    public List<PeerReview> getReviewsByCourse(Long courseId) {
        Optional<Course> entity = courseRepos.findById(courseId);
        if(entity.isPresent()) {
            return peerReviewRepos.findPeerReviewByCourse(entity.get());
        }
        throw new EntityNotFoundException("the course with id " + courseId + " not found");
    }
    @Override
    public List<PeerReview> getReviewsByStudent(Long studentId) {
        Optional<Student> entity = studentRepos.findById(studentId);
        if(entity.isPresent()) {
            return peerReviewRepos.findPeerReviewByStudent(entity.get());
        }
        throw new EntityNotFoundException("the student with id " + studentId + " not found");
    }
    @Override
    public void savePeerReview(ReviewRequest reviewRequest) {
        PeerReview peerReview = new PeerReview();
        
        Optional<Student> studentEntity = studentRepos.findById(reviewRequest.getStudentId());
       if(!studentEntity.isPresent()) {
        throw new EntityNotFoundException("the student " + reviewRequest.getStudentId() + " not found");
       }
       Student student = studentEntity.get();
        
       Optional<Course> courseEntity = courseRepos.findById(reviewRequest.getCourseId());
       if(!courseEntity.isPresent()) {
        throw new EntityNotFoundException("the course " + reviewRequest.getCourseId() + " not found");
       }
       Course course = courseEntity.get();

       Optional<Account> accountEntity = accountRepos.findById(reviewRequest.getAccountId());
       if(!accountEntity.isPresent()) {
        throw new EntityNotFoundException("the course " + reviewRequest.getAccountId() + " not found");
       }
       Account account = accountEntity.get();

       List<CourseQuestion> courseQuestions = course.getCourseQuestions();
       System.out.println(courseQuestions.size() + " :size");

       List<Question> list = new ArrayList<>();

    for(CourseQuestion cquestion : courseQuestions) {
        Question question = new Question(cquestion.getTitle(), peerReview);
        System.out.println(question);
        list.add(question);
        
    }
        peerReview.setQuestions(list);
       peerReview.setAccount(account);
       peerReview.setStudent(student);
       peerReview.setCourse(course);
       peerReviewRepos.save(peerReview);
    }
    @Override
    public List<Question> getReviewQuestion(Long reviewId) {
        Optional<PeerReview> entity = peerReviewRepos.findById(reviewId);
        if(entity.isPresent()) {
            PeerReview review = entity.get();
            return review.getQuestions();
        }
        throw new EntityNotFoundException("the review with reviewId " + reviewId + " not found");
    }

   
    
}
