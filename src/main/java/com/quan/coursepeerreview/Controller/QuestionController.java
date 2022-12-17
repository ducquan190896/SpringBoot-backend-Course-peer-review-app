package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Request.QuestionRequest;
import com.quan.coursepeerreview.Service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAll() {
        return new ResponseEntity<>(questionService.getQuestions(), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        return new ResponseEntity<Question>(questionService.getQuestion(id), HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<HttpStatus> updateQuestion(@Valid @RequestBody QuestionRequest questionRequest, @PathVariable Long id) {
        questionService.updateQuestion(id, questionRequest);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
