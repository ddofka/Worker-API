package org.example.workerapi.converter;

import org.example.workerapi.dto.CreateEmployeeRequest;
import org.example.workerapi.dto.GetEmployeeResponse;
import org.example.workerapi.entity.Department;
import org.example.workerapi.entity.Employee;
import org.example.workerapi.entity.Project;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeConverter {

    public GetEmployeeResponse entityToDto(Employee employee) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        response.setId(employee.getId());
        response.setLastName(employee.getLastName());
        response.setWorksFrom(employee.getWorksFrom());
        response.setDepartment(employee.getDepartment().getTitle());
        response.setProject(employee.getProject().getTitle());
        return response;
    }

    public List<GetEmployeeResponse> entityListToDto(List<Employee> employees){
        List<GetEmployeeResponse> responses = new ArrayList<>();
        for (Employee employee : employees) {
            responses.add(entityToDto(employee));
        }
        return responses;
    }

    public Employee toEntity(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setPersonalCode(request.getPersonalCode());
        employee.setLastName(request.getLastName());
        employee.setBirthDate(request.getBirthDate());
        employee.setWorksFrom(request.getWorksFrom());
        employee.setPosition(request.getPosition());
        employee.setDepartment(new Department());
        employee.setProject(new Project());
        return employee;
    }

    public List<Employee> toEntityList(List<CreateEmployeeRequest> requests) {
        List<Employee> employees = new ArrayList<>();
        for (CreateEmployeeRequest request : requests) {
            employees.add(toEntity(request));
        }
        return employees;
    }

}
