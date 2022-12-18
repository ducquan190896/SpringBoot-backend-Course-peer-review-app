package com.quan.coursepeerreview.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.quan.coursepeerreview.Security.Filter.ExceptionFilter;
import com.quan.coursepeerreview.Security.Filter.FilterAuthentication;
import com.quan.coursepeerreview.Security.Filter.FilterJwtAuthorization;




@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    CustomAuthenticationManager customAuthenticationManager;
    @Autowired
    ExceptionFilter exceptionFilter;

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  
        FilterAuthentication filterAuthentication = new FilterAuthentication(customAuthenticationManager);
        filterAuthentication.setFilterProcessesUrl("/login");

        http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/api/account/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(exceptionFilter, filterAuthentication.getClass())
        .addFilter(filterAuthentication)
        .addFilterAfter(new FilterJwtAuthorization(), filterAuthentication.getClass())
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
