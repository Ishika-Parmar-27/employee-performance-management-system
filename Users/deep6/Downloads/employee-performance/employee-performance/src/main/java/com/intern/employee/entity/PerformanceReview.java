package com.intern.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewPeriod;

    private Integer rating;

    private String comments;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}