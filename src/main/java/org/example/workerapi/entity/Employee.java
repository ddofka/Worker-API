package org.example.workerapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(exclude = {"department", "project"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personalCode;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate worksFrom;
    private String position;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Department department;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Project project;

}
