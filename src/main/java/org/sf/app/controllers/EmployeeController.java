package org.sf.app.controllers;

import java.util.List;

import org.sf.app.entities.Employee;
import org.sf.app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/employes")
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepo;

	@GetMapping()
	public List<Employee> index(){
		return employeeRepo.findAll();
	}
	
	@PostMapping()
	public Employee store(@RequestBody Employee employee) {
		return employeeRepo.save(employee);		
	}
}
