package com.quan.coursepeerreview.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Repository.AccountRepos;
import com.quan.coursepeerreview.Service.AccountService;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    AccountService accountService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      
       Account account = accountService.getAccountByUsername(authentication.getName());
       if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            throw new BadCredentialsException("the password is wrong");
       }
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(account.getRole().getName()));

        return new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), list);
    }
}
