package com.quan.coursepeerreview.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Student;

@Repository
public interface StudentRepos extends JpaRepository<Student, Long> {
    
}
