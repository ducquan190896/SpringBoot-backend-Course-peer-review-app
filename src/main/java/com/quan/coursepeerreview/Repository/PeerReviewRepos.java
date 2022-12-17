package com.quan.coursepeerreview.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Student;

@Repository
public interface PeerReviewRepos extends JpaRepository<PeerReview, Long> {
    
    List<PeerReview> findPeerReviewByStudent(Student student);
    List<PeerReview> findPeerReviewByCourse(Course course);
    List<PeerReview> findPeerReviewByAccount(Account account);
    
}
