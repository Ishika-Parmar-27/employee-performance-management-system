package com.intern.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.employee.entity.PerformanceReview;
import com.intern.employee.security.JwtAuthenticationFilter;
import com.intern.employee.service.PerformanceReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PerformanceReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
class PerformanceReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PerformanceReviewService reviewService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testGetReviewById() throws Exception {

        PerformanceReview review = new PerformanceReview();
        review.setId(1L);

        when(reviewService.getReviewById(1L))
                .thenReturn(review);

        mockMvc.perform(get("/reviews/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllReviews() throws Exception {

        when(reviewService.getAllReviews())
                .thenReturn(List.of(new PerformanceReview()));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateReview() throws Exception {

        PerformanceReview review = new PerformanceReview();

        when(reviewService.createReview(any(PerformanceReview.class)))
                .thenReturn(review);

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk());
    }
}