package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Request.ReviewRequest;
import com.quan.coursepeerreview.Service.PeerReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/review")
public class PeerReviewController {
    @Autowired
    PeerReviewService peerReviewService;

    @GetMapping("/all")
    public ResponseEntity<List<PeerReview>> getAll() {
        return new ResponseEntity<>(peerReviewService.getReviews(), HttpStatus.OK);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<List<PeerReview>> getAllByAccount(@PathVariable Long id) {
        return new ResponseEntity<>(peerReviewService.getReviewsByAccount(id), HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<List<PeerReview>> getAllByStudent(@PathVariable Long id) {
        return new ResponseEntity<>(peerReviewService.getReviewsByStudent(id), HttpStatus.OK);
    }
    @GetMapping("/course/{id}")
    public ResponseEntity<List<PeerReview>> getAllByCourse(@PathVariable Long id) {
        return new ResponseEntity<>(peerReviewService.getReviewsByCourse(id), HttpStatus.OK);
    }
    @GetMapping("/{id}/getquestions")
    public ResponseEntity<List<Question>> getReviewQuestion(@PathVariable Long id) {
        return new ResponseEntity<List<Question>>(peerReviewService.getReviewQuestion(id), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveReview(@Valid @RequestBody ReviewRequest ReviewRequest) {
        peerReviewService.savePeerReview(ReviewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
