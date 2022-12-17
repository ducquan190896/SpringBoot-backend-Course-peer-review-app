package com.quan.coursepeerreview.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.coursepeerreview.Entity.Student;
import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Repository.StudentRepos;
import com.quan.coursepeerreview.Service.StudentService;

@Service
public class StudentServiceIml  implements StudentService{

    @Autowired
    StudentRepos studentRepos;

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> entity = studentRepos.findById(id);
        Student student = isCheck(entity, id);
        studentRepos.delete(student);
    }

    @Override
    public Student getStudentById(Long id) {
       Optional<Student> entity = studentRepos.findById(id);
       Student student = isCheck(entity, id);
       return student;
    }

    @Override
    public Student getStudentByStudentnumber(String studentnumber) {
        Optional<Student> entity = studentRepos.findByStudentnumber(studentnumber);
        Student student =  isCheck(entity, 404L);
        return student;
    }

    @Override
    public List<Student> getStudents() {
       return studentRepos.findAll();
    }

    @Override
    public List<Student> getStudentsByFaculty(String faculty) {
        return studentRepos.findStudentByFaculty(faculty);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> entity = studentRepos.findById(id);
        Student student2 = isCheck(entity, id);
        student2.setFirstname(student.getFirstname());
        student2.setLastname(student.getLastname());
        student2.setFaculty(student.getFaculty());
      return  studentRepos.save(student2);

    }
    
    private Student isCheck(Optional<Student> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the student with id " +  id + " not found");
    }
}
