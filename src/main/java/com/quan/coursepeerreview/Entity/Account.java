package com.quan.coursepeerreview.Entity;




import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "account")
@Entity(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @SequenceGenerator(
        name = "account_sequence",
        sequenceName = "account_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "account_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "username cannot be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    
    @OneToMany(mappedBy = "account", orphanRemoval = true, fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
    private Set<PeerReview> peerReviews = new HashSet<>();

    public Account( String username,
            String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account( String username, String password, Role role, Student student) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.student = student;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
    }
    
    
    
}


