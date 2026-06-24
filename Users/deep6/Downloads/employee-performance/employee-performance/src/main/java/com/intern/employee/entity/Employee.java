package com.intern.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String designation;

    private Double salary;
     @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
@JoinTable(
        name = "employee_project",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
)
private List<Project> projects;
    @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "review_id")
   private PerformanceReview performanceReview;

    @Enumerated(EnumType.STRING)
    private Role role;
}