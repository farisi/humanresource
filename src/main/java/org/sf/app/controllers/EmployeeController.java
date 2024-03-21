package org.sf.app.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.sf.app.entities.Employee;
import org.sf.app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PatchMapping("/{id}")
	public Employee update(@PathVariable long id, @RequestBody  Employee employee)
	{
		Optional<Employee> updateEmploye = employeeRepo.findById(id);
		if(updateEmploye.isPresent()) {
			Employee uEmp = updateEmploye.get();
			uEmp.setAddress(employee.getAddress());
			uEmp.setFirstName(employee.getFirstName());
			uEmp.setLastName(employee.getLastName());
			uEmp.setJoinDate(employee.getJoinDate());
			uEmp.setMobile(employee.getMobile());
			uEmp.setPlaceOfBirth(employee.getPlaceOfBirth());
			uEmp.setBirthDate(employee.getBirthDate());
			uEmp.setUpdatedAt(LocalDateTime.now());
			return employeeRepo.save(uEmp);
		}
		return null;
	}
}
