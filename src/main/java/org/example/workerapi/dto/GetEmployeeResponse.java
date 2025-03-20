package org.example.workerapi.dto;

import java.time.LocalDate;


public record GetEmployeeResponse(
        Long id,
        String lastName,
        LocalDate worksFrom,
        String department,
        String project
) {}
