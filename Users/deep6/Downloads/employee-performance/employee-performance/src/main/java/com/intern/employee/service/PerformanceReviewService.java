package com.intern.employee.service;

import com.intern.employee.entity.PerformanceReview;
import com.intern.employee.exception.ResourceNotFoundException;
import com.intern.employee.repository.PerformanceReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceReviewService {

    private final PerformanceReviewRepository reviewRepository;

    public PerformanceReviewService(PerformanceReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Create Review
    public PerformanceReview createReview(PerformanceReview review) {
        return reviewRepository.save(review);
    }

    // Get All Reviews
    public List<PerformanceReview> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get Review By Id
    public PerformanceReview getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
    }

    // Update Review
    public PerformanceReview updateReview(Long id, PerformanceReview updatedReview) {

        PerformanceReview review = getReviewById(id);

        review.setReviewPeriod(updatedReview.getReviewPeriod());
        review.setRating(updatedReview.getRating());
        review.setComments(updatedReview.getComments());
        review.setEmployee(updatedReview.getEmployee());

        return reviewRepository.save(review);
    }

    // Delete Review
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}