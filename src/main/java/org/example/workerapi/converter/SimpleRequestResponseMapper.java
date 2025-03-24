package org.example.workerapi.converter;

import org.example.workerapi.dto.CreateEmployeeRequest;
import org.example.workerapi.dto.GetEmployeeResponse;
import org.example.workerapi.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SimpleRequestResponseMapper {

    // Mapping from CreateEmployeeRequest to Employee (for creating/updating)
    @Mapping(target = "id", ignore = true) // ID is auto-generated
    @Mapping(target = "department", ignore = true) // Not in request
    @Mapping(target = "project", ignore = true) // Not in request
    Employee toEmployee(CreateEmployeeRequest request);

    // Mapping from Employee to GetEmployeeResponse (for returning data)
    @Mapping(source = "department.title", target = "department")
    @Mapping(source = "project.title", target = "project")
    GetEmployeeResponse toResponse(Employee employee);

    // Mapping a list of Employee to a list of GetEmployeeResponse (for GET list)
    List<GetEmployeeResponse> toResponseList(List<Employee> employees);
}
