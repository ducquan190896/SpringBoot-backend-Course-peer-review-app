package com.quan.coursepeerreview.Security.Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Security.CustomAuthenticationManager;
import com.quan.coursepeerreview.Security.SecurityConstant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterAuthentication extends UsernamePasswordAuthenticationFilter {
    @Autowired
    CustomAuthenticationManager customAuthenticationManager;    
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
      try {
        Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
        return customAuthenticationManager.authenticate(authentication);
      } catch (IOException ex) {
        throw new RuntimeException(ex.getMessage());
      }
       
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
       List<String> claims = authResult.getAuthorities().stream().map(au -> au.getAuthority()).collect(Collectors.toList());

       String token = JWT.create()
                    .withSubject(authResult.getName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.expiry_time))
                    .withClaim("authorities", claims)
                    .sign(Algorithm.HMAC512(SecurityConstant.private_key));

            response.setHeader("Authorization", SecurityConstant.authorization + token);
                    
    }
}
