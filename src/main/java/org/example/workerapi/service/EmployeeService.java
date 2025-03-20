package org.example.workerapi.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.workerapi.entity.Department;
import org.example.workerapi.entity.Employee;
import org.example.workerapi.entity.Project;
import org.example.workerapi.exception.EmployeeNotFoundException;
import org.example.workerapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void printAllEmployees() {
        getAllEmployees().forEach(System.out::println);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Transactional
    public void addTestEmployees(){
        Random random = new Random();
        Project project1 = new Project();
        project1.setTitle("Project Fire");
        Project project2 = new Project();
        project2.setTitle("Project Water");
        Project project3 = new Project();
        project3.setTitle("Project Earth");

        Department department1 = new Department();
        department1.setTitle("Department Ice");
        Department department2 = new Department();
        department2.setTitle("Department Wind");
        Department department3 = new Department();
        department3.setTitle("Department Stone");

        for (int i = 0; i < 12; i++) {
            int birthYearOffset = 18 + random.nextInt(47);
            LocalDate birthDate = LocalDate.now()
                    .minusYears(birthYearOffset)
                    .minusDays(random.nextInt(365));

            Employee employee = new Employee();
            employee.setPersonalCode("1234567891" + i);
            employee.setName("Employee " + i);
            employee.setLastName("Last Name " + i);
            employee.setBirthDate(birthDate);
            employee.setWorksFrom(LocalDate.now().minusYears(random.nextInt(10)));
            employee.setPosition("Position " + i);

            if (i < 4) {
                employee.setDepartment(department1);
                employee.setProject(project1);
                department1.setManagerPersonalCode(employee.getPersonalCode());
            } else if (i < 8) {
                employee.setDepartment(department2);
                employee.setProject(project2);
                department2.setManagerPersonalCode(employee.getPersonalCode());
            } else {
                employee.setDepartment(department3);
                employee.setProject(project3);
                department3.setManagerPersonalCode(employee.getPersonalCode());
            }

            addEmployee(employee);
        }
    }

    @Transactional
    public void deleteEmployeeByID(Long id) {
        Optional<Employee> maybeEmployeeFromDb = employeeRepository.findById(id);
        if (maybeEmployeeFromDb.isEmpty()) {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    public Employee patchEmployeeById(Long id, Employee employeeFromRequest) {
        Employee employeeFromDb = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
//        if (maybeEmployeeFromDb.isEmpty()) {
//            throw new EntityNotFoundException("Employee with id " + id + " not found");
//        }
//        Employee employeeFromDb = maybeEmployeeFromDb.get();

        if (StringUtils.isNotBlank(employeeFromRequest.getPersonalCode()) &&
            !employeeFromRequest.getPersonalCode().equals(employeeFromDb.getPersonalCode())){
            employeeFromDb.setPersonalCode(employeeFromRequest.getPersonalCode());
        }
        if (StringUtils.isNotBlank(employeeFromRequest.getName()) &&
                !employeeFromRequest.getName().equals(employeeFromDb.getName())){
            employeeFromDb.setName(employeeFromRequest.getName());
        }
        if (StringUtils.isNotBlank(employeeFromRequest.getLastName()) &&
                !employeeFromRequest.getLastName().equals(employeeFromDb.getLastName())){
            employeeFromDb.setLastName(employeeFromRequest.getLastName());
        }
        if (StringUtils.isNotBlank(employeeFromRequest.getPosition()) &&
                !employeeFromRequest.getPosition().equals(employeeFromDb.getPosition())){
            employeeFromDb.setPosition(employeeFromRequest.getPosition());
        }
        if (employeeFromRequest.getBirthDate() != null &&
            !employeeFromRequest.getBirthDate().equals(employeeFromDb.getBirthDate())){
            employeeFromDb.setBirthDate(employeeFromRequest.getBirthDate());
        }

        if (employeeFromRequest.getWorksFrom() != null &&
            !employeeFromRequest.getWorksFrom().equals(employeeFromDb.getWorksFrom())){
            employeeFromDb.setWorksFrom(employeeFromRequest.getWorksFrom());
        }
        return employeeRepository.saveAndFlush(employeeFromDb);
    }

}
