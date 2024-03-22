package org.sf.app.repositories;

import org.sf.app.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends  PagingAndSortingRepository<Employee,Long>, ListCrudRepository<Employee, Long>{

	@Query("SELECT e FROM Employee e WHERE firstName like concat(:keyword, '%') or lastName like concat(:keyword, '%') or email like concat(:keyword, '%') ")
	Page<Employee> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}