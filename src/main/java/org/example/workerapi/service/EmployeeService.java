package org.example.workerapi.service;

import lombok.RequiredArgsConstructor;
import org.example.workerapi.entity.Department;
import org.example.workerapi.entity.Employee;
import org.example.workerapi.entity.Project;
import org.example.workerapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void printAllEmployees() {
        for (Employee employee : findAllEmployees()) {
            System.out.println(employee);
        }
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public void addTestEmployees(){
        Random random = new Random();
        for (int i = 0; i <= 10; i++) {
            int birthYearOffset = 18 + random.nextInt(47);
            LocalDate birthDate = LocalDate.now()
                    .minusYears(birthYearOffset)
                    .minusDays(random.nextInt(365));
            Employee employee = new Employee();
            employee.setPersonalCode(1234567891+i);
            employee.setName("Employee "+i);
            employee.setLastName("Last Name "+i);
            employee.setBirthDate(birthDate);
            employee.setWorksFrom(LocalDate.now().minusYears(random.nextInt(10)));
            employee.setPosition("position "+i);
            addEmployee(employee);

        }
    }

}
