package com.quan.coursepeerreview.Security.Filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quan.coursepeerreview.Security.SecurityConstant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterJwtAuthorization extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith(SecurityConstant.authorization)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.replace(SecurityConstant.authorization, "");

       try {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstant.private_key)).build().verify(token);
        String username = decodedJWT.getSubject();
        List<String> claims = decodedJWT.getClaim("authorities").asList(String.class);
        List<SimpleGrantedAuthority> list = claims.stream().map(aut -> new SimpleGrantedAuthority(aut)).collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, list);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
       } catch (ServletException ex) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(ex.getMessage());
        response.getWriter().flush();
       } catch (IOException ex) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(ex.getMessage());
        response.getWriter().flush();
       }
    }
}
