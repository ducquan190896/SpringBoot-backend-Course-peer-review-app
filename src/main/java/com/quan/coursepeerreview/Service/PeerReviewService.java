package com.quan.coursepeerreview.Service;

import java.util.List;

import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Request.ReviewRequest;

public interface PeerReviewService {
    
    void savePeerReview(ReviewRequest reviewRequest);
    List<PeerReview> getReviews();
    List<PeerReview> getReviewsByCourse(Long courseId);
    List<PeerReview> getReviewsByStudent(Long studentId);
    List<PeerReview> getReviewsByAccount(Long accountId);
    void deleteReview(Long id);
    List<Question> getReviewQuestion(Long reviewId);
}
