package com.quan.coursepeerreview.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.PeerReview;

@Repository
public interface PeerReviewRepos extends JpaRepository<PeerReview, Long> {
    
}
