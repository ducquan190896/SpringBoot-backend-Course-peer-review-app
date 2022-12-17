package com.quan.coursepeerreview;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.quan.coursepeerreview.Exception.EntityNotFoundException;
import com.quan.coursepeerreview.Exception.ErrorResponse;

@ControllerAdvice
public class CoursePeerExceptionHandling {
    
    @ExceptionHandler(EntityNotFoundException.class) 
    public ResponseEntity<Object> HandleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponse err = new ErrorResponse(ex, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }
    
}
