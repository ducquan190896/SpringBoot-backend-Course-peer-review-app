package com.quan.coursepeerreview.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.coursepeerreview.Entity.Account;

@Repository
public interface AccountRepos extends JpaRepository<Account, Long> {
    
}
