package com.quan.coursepeerreview.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "course")
@Entity(name = "Course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @SequenceGenerator(
        name = "course_sequence",
        sequenceName = "course_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotBlank(message = "coursecode cannot be blank")
    @Column(name = "coursecode", nullable = false, unique = true)
    private String coursecode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CourseStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdby")
    private LocalDateTime createdby;

    @JsonIgnore
    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseQuestion> courseQuestions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")

    )
    private Set<Student> students = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", orphanRemoval = true, fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<PeerReview> peerReviews = new HashSet<>();

    public Course( String name, String coursecode, CourseStatus status, LocalDateTime createdby) {
        this.name = name;
        this.coursecode = coursecode;
        this.status = status;
        this.createdby = createdby;
    }

    public Course( String name, String coursecode, CourseStatus status) {
        this.name = name;
        this.coursecode = coursecode;
        this.status = status;
        
    }


    
}
