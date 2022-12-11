package com.quan.coursepeerreview.Entity;



import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "student")
@Entity(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message  = "studentnumber cannot be blank")
    @Column(name = "studentnumber", nullable = false, unique = true)
    private String studentnumber;

    @NotBlank(message = "lastname cannot be blank")
    @Column(name = "lastname", nullable = false)
    private String lastname;
    
    @NotBlank(message = "firstname cannot be blank")
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank(message = "faculty cannot be blank")
    @Column(name = "faculty", nullable = false)
    private String faculty;

    
    @Column(name = "group")
    private String group;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.MERGE)
    private Set<Course> courses = new HashSet<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY, orphanRemoval = true)
    private Account account;

    @OneToMany(mappedBy = "student", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PeerReview> peerReviews = new HashSet<>();

    public Student( String studentnumber,
             String lastname,
            String firstname,
             String faculty, String group) {
        this.studentnumber = studentnumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.faculty = faculty;
        this.group = group;
    }

    public Student( String studentnumber,
    String lastname,
   String firstname,
    String faculty) {
this.studentnumber = studentnumber;
this.lastname = lastname;
this.firstname = firstname;
this.faculty = faculty;

}


}
