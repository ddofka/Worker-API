package org.example.workerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.workerapi.converter.EmployeeConverter;
import org.example.workerapi.dto.CreateEmployeeRequest;
import org.example.workerapi.dto.GetEmployeeResponse;
import org.example.workerapi.entity.Employee;
import org.example.workerapi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/employees")
@RestController()
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeConverter converter;

    @GetMapping
    public List<GetEmployeeResponse> getEmployeeList(@RequestParam(required = false) Long id) {
        return converter.entityListToDto(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public GetEmployeeResponse getEmployeeById(@PathVariable Long id) {
        return converter.entityToDto(employeeService.findEmployeeById(id));
    }

    @PostMapping
    public GetEmployeeResponse createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = converter.toEntity(request);
        GetEmployeeResponse response = converter.entityToDto(employeeService.addEmployee(employee));
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeByID(id);
    }

    @PatchMapping("/{id}")
    public GetEmployeeResponse patchEmployeeById(@PathVariable Long id, @RequestBody CreateEmployeeRequest request) {
        Employee employee = converter.toEntity(request);
        return converter.entityToDto(employeeService.patchEmployeeById(id, employee));
    }

}
