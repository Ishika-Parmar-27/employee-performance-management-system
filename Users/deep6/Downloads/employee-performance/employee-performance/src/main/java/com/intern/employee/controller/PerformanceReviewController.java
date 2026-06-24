package com.intern.employee.controller;

import com.intern.employee.entity.PerformanceReview;
import com.intern.employee.service.PerformanceReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class PerformanceReviewController {

    private final PerformanceReviewService reviewService;

    public PerformanceReviewController(PerformanceReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create
    @PostMapping
    public PerformanceReview createReview(@RequestBody PerformanceReview review) {
        return reviewService.createReview(review);
    }

    // Get All
    @GetMapping
    public List<PerformanceReview> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Get By Id
    @GetMapping("/{id}")
    public PerformanceReview getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // Update
    @PutMapping("/{id}")
    public PerformanceReview updateReview(
            @PathVariable Long id,
            @RequestBody PerformanceReview review) {

        return reviewService.updateReview(id, review);
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {

        reviewService.deleteReview(id);

        return "Review deleted successfully";
    }
}