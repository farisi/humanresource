package org.sf.app.repositories;

import org.sf.app.entities.JobExperience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobExperienceRepository extends JpaRepository<JobExperience, Integer>{
	
	@EntityGraph(attributePaths = "employee")
	Page<JobExperience> findAll(Pageable pageable);

	@EntityGraph(attributePaths = "employee")
	@Query("SELECT j FROM JobExperience j  WHERE j.companyName like concat(:keyword, '%') and j.position like concat(:keyword,'%')")
	Page<JobExperience> findByKeyword(String keyword, Pageable pageable);

}
