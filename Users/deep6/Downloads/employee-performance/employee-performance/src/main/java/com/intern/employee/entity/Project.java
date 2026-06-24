package com.intern.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String description;

    private LocalDate deadline;
    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Employee> employees;
}