package com.quan.coursepeerreview.Service;

import java.util.List;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Role;
import com.quan.coursepeerreview.Entity.Request.AccountRequest;
import com.quan.coursepeerreview.Entity.Request.ChangePassword;
import com.quan.coursepeerreview.Entity.Request.StudentRegister;
import com.quan.coursepeerreview.Entity.Request.TeacherRegister;

public interface AccountService {
    List<Account> getAccounts();
    List<Account> getAccountsByRole(Role role);
    Account getAccount(Long id);
    Account getAccountByUsername(String username);
    void saveAccount(StudentRegister studentRegister);
    void deleteAccount(Long id);
    Account updateAccount( ChangePassword changePassword);
    void saveTeacher(TeacherRegister teacherRegister);
}
