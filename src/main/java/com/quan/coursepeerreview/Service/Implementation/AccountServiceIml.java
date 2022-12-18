package com.quan.coursepeerreview.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Role;
import com.quan.coursepeerreview.Entity.Student;
import com.quan.coursepeerreview.Entity.Request.AccountRequest;
import com.quan.coursepeerreview.Entity.Request.ChangePassword;
import com.quan.coursepeerreview.Entity.Request.StudentRegister;
import com.quan.coursepeerreview.Entity.Request.TeacherRegister;
import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Repository.AccountRepos;
import com.quan.coursepeerreview.Repository.StudentRepos;
import com.quan.coursepeerreview.Service.AccountService;



@Service
public class AccountServiceIml implements AccountService, UserDetailsService{
    @Autowired
    AccountRepos accountRepos;

   

    @Autowired
    StudentRepos studentRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> entity = accountRepos.findAccountByUsername(username);
        Account account = isCheck(entity, 404L);
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(account.getRole().getName()));
        return new User(account.getUsername(), account.getPassword(), list);
    }
    

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> entity = accountRepos.findById(id);
        Account account = isCheck(entity, id);
        accountRepos.delete(account);
        
    }

    @Override
    public Account getAccount(Long id) {
        Optional<Account> entity = accountRepos.findById(id);
        Account account = isCheck(entity, id);
        return account;

    }

    @Override
    public Account getAccountByUsername(String username) {
            Optional<Account> entity = accountRepos.findAccountByUsername(username);
            Account account = isCheck(entity, 404L);
            return account;
    }

    @Override
    public List<Account> getAccounts() {
       return accountRepos.findAll();
    }

    @Override
    public List<Account> getAccountsByRole(Role role) {
       return accountRepos.findAccountByRole(role);
    }

    @Override
    public void saveAccount(StudentRegister studentRegister) {
        Account account = new Account();
        account.setUsername(studentRegister.getUsername());
        if(!studentRegister.getPassword().equals(studentRegister.getConfirmedPassword())) {
          throw new EntityNotFoundException("passwords dont match to each other");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(studentRegister.getConfirmedPassword()));
        account.setRole(Role.USER);
       
        

    Optional<Student> studentEntity = studentRepos.findByStudentnumber(studentRegister.getStudentnumber());
    if(studentEntity.isPresent()) {
        throw new EntityNotFoundException("the student with student number " + studentRegister.getStudentnumber() + " already exist");
    }

    Student student = new Student(studentRegister.getStudentnumber(), studentRegister.getLastname(), studentRegister.getFirstname(), studentRegister.getFaculty());
     account.setStudent(student);
    // studentRepos.save(student);
    accountRepos.save(account);
    }

    @Override
    public Account updateAccount( ChangePassword changePassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        Optional<Account> entity = accountRepos.findAccountByUsername(authentication.getName());
        Account account = isCheck(entity, 404L );
        System.out.println(account.getPassword());
        System.out.println(new BCryptPasswordEncoder().encode(changePassword.getCurrentPassword()).toString());

        if(!(new BCryptPasswordEncoder().matches(changePassword.getCurrentPassword(), account.getPassword())) ) {
            throw new EntityNotFoundException("the current password not correct");
        }
        if( !changePassword.getPassword().equals(changePassword.getConfirmedPassword())) {
            throw new EntityNotFoundException("the new password not correct");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(changePassword.getPassword()));
        return accountRepos.save(account);
    }
    private Account isCheck(Optional<Account> entity, Long id) {
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the account not found");
        }
        return entity.get();
    }

    @Override
    public void saveTeacher(TeacherRegister teacherRegister) {
        Account account = new Account();
        account.setUsername(teacherRegister.getUsername());
        if(!teacherRegister.getPassword().equals(teacherRegister.getConfirmedPassword())) {
          throw new EntityNotFoundException("passwords dont match to each other");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(teacherRegister.getConfirmedPassword()));
        account.setRole(Role.USER);
       
        

    Optional<Student> studentEntity = studentRepos.findByStudentnumber(teacherRegister.getStudentnumber());
    if(studentEntity.isPresent()) {
        throw new EntityNotFoundException("the student with student number " + teacherRegister.getStudentnumber() + " already exist");
    }

    Student student = new Student(teacherRegister.getStudentnumber(), teacherRegister.getLastname(), teacherRegister.getFirstname(), teacherRegister.getFaculty());
     account.setStudent(student);
    // studentRepos.save(student);
    accountRepos.save(account);
    }
    
}
