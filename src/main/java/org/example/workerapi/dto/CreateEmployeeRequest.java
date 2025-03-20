package org.example.workerapi.dto;

import java.time.LocalDate;

public record CreateEmployeeRequest(

    String personalCode,
    String name,
    String lastName,
    String position,
    LocalDate birthDate,
    LocalDate worksFrom
){}
