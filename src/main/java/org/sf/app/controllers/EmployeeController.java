package org.sf.app.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.sf.app.entities.Employee;
import org.sf.app.entities.JobExperience;
import org.sf.app.repositories.EmployeeRepository;
import org.sf.app.repositories.JobExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@RequestMapping("/api/employes")
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	JobExperienceRepository jeRepo;

	@GetMapping()
	public Page<Employee> index(
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int pagenum,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "firstName,asc") String[] sort
		){
		
		String sortField = sort[0];
		String sortDirection = sort[1];
			
		Direction direction;
		if (sortDirection.equals("desc")) {
			direction = Sort.Direction.DESC;
		} else {
			direction = Sort.Direction.ASC;
		}
		Order order = new Order(direction, sortField);

		Pageable pageable = PageRequest.of(pagenum - 1, pagesize, Sort.by(order));

		Page<Employee> pageEmployee;
		
		if (keyword == null) {
			pageEmployee = employeeRepo.findAll(pageable);
		} else {
			pageEmployee = employeeRepo.findByKeyword(keyword,pageable);
		}

		return pageEmployee;
	}
	
	@PostMapping()
	public Employee store(@RequestBody Employee employee) {
		return employeeRepo.save(employee);		
	}
	
	@PatchMapping("/{id}")
	public Employee update(@PathVariable long id, @RequestBody  Employee employee)
	{
		Optional<Employee> updateEmploye = employeeRepo.findById(id);
		Employee uEmp=null;
		if(updateEmploye.isPresent()) {
			uEmp = updateEmploye.get();
			uEmp.setAddress(employee.getAddress());
			uEmp.setFirstName(employee.getFirstName());
			uEmp.setLastName(employee.getLastName());
			uEmp.setJoinDate(employee.getJoinDate());
			uEmp.setMobile(employee.getMobile());
			uEmp.setPlaceOfBirth(employee.getPlaceOfBirth());
			uEmp.setBirthDate(employee.getBirthDate());
			uEmp.setUpdatedAt(LocalDateTime.now());
			uEmp.setSalary(employee.getSalary());
			return employeeRepo.save(uEmp);
		}
		return uEmp;
	}
	
	@DeleteMapping("/{id}")
	public Boolean delete(@PathVariable Long id) {
		Optional<Employee> updateEmploye = employeeRepo.findById(id);
		if(updateEmploye.isPresent()) {
			employeeRepo.delete(updateEmploye.get());
			return true;
		}
		else {
			return false;
		}
	}
	
	@PostMapping("/{id}/store0")
	public JobExperience addJobExperience(@PathVariable Long id, @RequestBody JobExperience je) {
		Optional<Employee> updateEmploye = employeeRepo.findById(id);
		je.setEmployee(updateEmploye.get());
		return jeRepo.save(je);
	}
}
