package com.quan.coursepeerreview.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "question")
@Entity(name = "Question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @SequenceGenerator(
        name = "question_sequence",
        sequenceName = "question_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "question_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Min(value = 0, message = "grade must by higher than 0")
    @Column(name = "grade", nullable = false)
    private double grade;

    @NotBlank(message = "title cannot be blank")
    @Column(name = "title", nullable = false)
    private String title;

    
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "peerreview_id", referencedColumnName = "id")
    private PeerReview peerReview;

    public Question(String title, PeerReview peerReview) {
        this.title = title;
        this.peerReview = peerReview;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", grade=" + grade + ", title=" + title + ", description=" + description + "]";
    }
    
}
