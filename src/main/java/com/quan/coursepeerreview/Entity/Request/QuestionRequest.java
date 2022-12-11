package com.quan.coursepeerreview.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String title;
    private double grade;
    private String description;
    private Long peerReviewId;

    
}
