package com.quan.coursepeerreview.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Question;

@Repository
public interface QuestionRepos extends JpaRepository<Question, Long> {
    
    Optional<Question> findByPeerReview(PeerReview peerReview);
}
