package com.quan.coursepeerreview.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "courseQuestion")
@Entity(name = "CourseQuestion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseQuestion {
    @Id
    @SequenceGenerator(
        name = "coursequestion_sequence",
        sequenceName = "coursequestion_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "coursequestion_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "title cannot be blank")
    @Column(name = "title", nullable = false)
    private String title;
    

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    public CourseQuestion( String title, Course course) {
        this.title = title;
        this.course = course;
    }
    
    
  
}
