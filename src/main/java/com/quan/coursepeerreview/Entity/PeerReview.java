package com.quan.coursepeerreview.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(
name = "peerReview", 
uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "course_id", "account_id"})})
@Entity(name = "PeerReview")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PeerReview {
    @Id
    @SequenceGenerator(
        name = "peerReview_sequence",
        sequenceName = "peerReview_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "peerReview_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Min(value = 0, message = "grade is not less than 0")
    @Column(name = "grade", updatable = false)
    private double grade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    // @JsonIgnore
    @OneToMany(mappedBy = "peerReview", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();


    public PeerReview(Student student, Course course, Account account) {
        this.student = student;
        this.course = course;
        this.account = account;
        this.grade = countGrade();
    }

    public double countGrade() {
        double grad = 0;
        if(this.questions.size() > 0) {
            grad = this.questions.stream().map(que -> que.getGrade()).mapToDouble(gra -> Double.valueOf(gra)).reduce(0, (a, b) -> a + b);
        } else {
            grad = 0;
        }
        return grad;
    }
    
}
