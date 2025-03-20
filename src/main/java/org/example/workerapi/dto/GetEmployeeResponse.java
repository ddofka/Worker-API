package org.example.workerapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetEmployeeResponse {

    private Long id;
    private String lastName;
    private LocalDate worksFrom;
    private String department;
    private String project;
}
