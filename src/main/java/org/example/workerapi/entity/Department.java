package org.example.workerapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "employee")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int managerPersonalCode;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employee = new ArrayList<>();

}
