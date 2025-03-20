package org.example.workerapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeRequest {

    private String personalCode;
    private String name;
    private String lastName;
    private String position;
    private LocalDate birthDate;
    private LocalDate worksFrom;
}
