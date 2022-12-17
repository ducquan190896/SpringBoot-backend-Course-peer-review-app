package com.quan.coursepeerreview.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuestionRequest {
    private String title;
    private Long courseId;
}
