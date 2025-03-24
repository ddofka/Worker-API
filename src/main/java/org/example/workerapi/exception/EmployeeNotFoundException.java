package org.example.workerapi.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String criteria) {
        super("Employee not found by: " + criteria);
    }
}