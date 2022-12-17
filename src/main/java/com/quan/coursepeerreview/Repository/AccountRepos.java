package com.quan.coursepeerreview.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Role;

@Repository
public interface AccountRepos extends JpaRepository<Account, Long> {
    
    // @Query(value = "select a.id, a.password, a.role, a.student_id, a.username from account a where a.role = ?", nativeQuery = true)
   List<Account> findAccountByRole(Role role);

   @Query(
    value = "select * from account where username = ?",
    nativeQuery = true
   )
   Optional<Account> findAccountByUsername(String username);
}
