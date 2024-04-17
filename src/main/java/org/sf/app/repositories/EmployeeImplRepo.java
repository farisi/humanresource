package org.sf.app.repositories;

import java.util.List;

import org.sf.app.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

public class EmployeeImplRepo {
	@Autowired
	EntityManager em;
	
	public List<Employee> findAll(){
		return null;
		
	}
}
