package com.quan.coursepeerreview.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Student;

@Repository
public interface StudentRepos extends JpaRepository<Student, Long> {
    
    @Query(
        value = "select * from student where studentnumber = ?",
        nativeQuery = true
    )
    Optional<Student> findByStudentnumber(String studentnumber);

    List<Student> findStudentByFaculty(String faculty);

}
