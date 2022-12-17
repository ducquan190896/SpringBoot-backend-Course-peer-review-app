package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Role;
import com.quan.coursepeerreview.Entity.Request.AccountRequest;
import com.quan.coursepeerreview.Entity.Request.ChangePassword;
import com.quan.coursepeerreview.Entity.Request.StudentRegister;
import com.quan.coursepeerreview.Entity.Request.TeacherRegister;
import com.quan.coursepeerreview.Repository.AccountRepos;
import com.quan.coursepeerreview.Service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<List<Account>>(accountService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Account>> getAccountsByRole(@PathVariable Role role) {
        return new ResponseEntity<>(accountService.getAccountsByRole(role), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return new ResponseEntity<Account>(accountService.getAccount(id), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> Register(@RequestBody StudentRegister studentRegister) {
        accountService.saveAccount(studentRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/teacherRegister")
    public ResponseEntity<HttpStatus> registerStudent(@RequestBody TeacherRegister teacherRegister) {
        accountService.saveTeacher(teacherRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/changepassword")
    public ResponseEntity<Account> ChangePassword(@RequestBody ChangePassword changePassword) {
        return new ResponseEntity<Account>(accountService.updateAccount( changePassword), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> DeleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
