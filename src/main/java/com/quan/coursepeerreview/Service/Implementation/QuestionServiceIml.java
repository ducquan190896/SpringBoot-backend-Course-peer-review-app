package com.quan.coursepeerreview.Service.Implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Request.QuestionRequest;
import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Repository.PeerReviewRepos;
import com.quan.coursepeerreview.Repository.QuestionRepos;
import com.quan.coursepeerreview.Service.QuestionService;

@Service
public class QuestionServiceIml implements QuestionService {
    @Autowired
    QuestionRepos questionRepos;
    @Autowired
    PeerReviewRepos peerReviewRepos;

    @Override
    public void updateQuestion(Long id, QuestionRequest questionRequest) {
        Optional<Question> entity = questionRepos.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the question with id " + id + " not found");
        }
        Question question = entity.get();
        question.setDescription(questionRequest.getDescription());
        question.setGrade(questionRequest.getGrade());
        
        PeerReview review = question.getPeerReview();
        review.getQuestions().stream().map(que -> que.getId() == question.getId() ? question : que).collect(Collectors.toList());
        review.setGrade(review.countGrade());
        peerReviewRepos.save(review);
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepos.findAll();
    }

    @Override
    public Question getQuestion(Long id) {
        Optional<Question> entity = questionRepos.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the question with id " + id + " not found");
        }
        Question question = entity.get();
        return question;
    }

    @Override
    public void deleteQuestion(Long id) {
        Optional<Question> entity = questionRepos.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the question with id " + id + " not found");
        }
        questionRepos.deleteById(id);;
        
    }

    
}
