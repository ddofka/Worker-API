package org.example.workerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.workerapi.converter.SimpleRequestResponseMapper;
import org.example.workerapi.dto.CreateEmployeeRequest;
import org.example.workerapi.dto.GetEmployeeResponse;
import org.example.workerapi.entity.Employee;
import org.example.workerapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/employees")
@RestController()
public class EmployeeController {

    private final EmployeeService employeeService;
    @Qualifier("simpleRequestResponseMapperImpl")
    private final SimpleRequestResponseMapper mapper;

    @GetMapping
    public List<GetEmployeeResponse> getEmployeeList(@RequestParam(required = false) Long id) {
        return mapper.toResponseList(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public GetEmployeeResponse getEmployeeById(@PathVariable Long id) {
        return mapper.toResponse(employeeService.findEmployeeById(id));
    }

    @PostMapping
    public GetEmployeeResponse createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = mapper.toEmployee(request);
        Employee savedEmployee = employeeService.addEmployee(employee);
        return mapper.toResponse(savedEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeByID(id);
    }

    @PatchMapping("/{id}")
    public GetEmployeeResponse patchEmployeeById(@PathVariable Long id, @RequestBody CreateEmployeeRequest request) {
        Employee employee = mapper.toEmployee(request);
        Employee updatedEmployee = employeeService.patchEmployeeById(id, employee);
        return mapper.toResponse(updatedEmployee);
    }

}
