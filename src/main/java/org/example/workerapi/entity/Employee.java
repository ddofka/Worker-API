package org.example.workerapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int personalCode;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate worksFrom;
    private String position;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Project project;

}
