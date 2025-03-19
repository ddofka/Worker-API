package org.example.workerapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Department {

    private String title;
    @Id
    private Long managerPersonalCode;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employee = new ArrayList<>();

}
