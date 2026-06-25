package com.intern.employee.service;

import com.intern.employee.entity.PerformanceReview;
import com.intern.employee.repository.PerformanceReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerformanceReviewServiceTest {

    @Mock
    private PerformanceReviewRepository reviewRepository;

    @InjectMocks
    private PerformanceReviewService performanceReviewService;

    @Test
    void testGetReviewById() {

        PerformanceReview review =
                new PerformanceReview();

        review.setId(1L);
        review.setRating(5);

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));

        PerformanceReview result =
                performanceReviewService.getReviewById(1L);

        assertNotNull(result);
        assertEquals(5, result.getRating());
    }

    @Test
    void testCreateReview() {

        PerformanceReview review =
                new PerformanceReview();

        review.setRating(5);

        when(reviewRepository.save(review))
                .thenReturn(review);

        PerformanceReview saved =
                performanceReviewService.createReview(review);

        assertNotNull(saved);
    }

    @Test
    void testDeleteReview() {

        doNothing()
                .when(reviewRepository)
                .deleteById(1L);

        performanceReviewService.deleteReview(1L);

        verify(reviewRepository)
                .deleteById(1L);
    }
}