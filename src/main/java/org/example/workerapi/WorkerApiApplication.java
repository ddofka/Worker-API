package org.example.workerapi;

import lombok.RequiredArgsConstructor;
import org.example.workerapi.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class WorkerApiApplication {

	private final EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(WorkerApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void test(){
		employeeService.addTestEmployees();
		//employeeService.printAllEmployees();
	}

}
