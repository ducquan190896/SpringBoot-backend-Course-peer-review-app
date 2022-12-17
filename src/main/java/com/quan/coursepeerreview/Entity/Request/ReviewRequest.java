package com.quan.coursepeerreview.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewRequest {
    private Long studentId;
    private Long courseId;
    private Long accountId;
}
