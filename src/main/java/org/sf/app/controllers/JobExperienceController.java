package org.sf.app.controllers;

import java.util.Optional;

import org.sf.app.entities.Employee;
import org.sf.app.entities.JobExperience;
import org.sf.app.repositories.JobExperienceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobExperienceController {
	
	@Autowired
	JobExperienceRepository jeRepo;
	
	Logger logger = LoggerFactory.getLogger(JobExperienceController.class);

	@GetMapping()
	public Page<JobExperience> index(
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int pagenum,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "companyName,asc") String[] sort){
		
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
		
		Page<JobExperience> jobExperiecePage;
		if (keyword == null) {
			jobExperiecePage = jeRepo.findAll(pageable);
		} else {
			jobExperiecePage = jeRepo.findByKeyword(keyword,pageable);
		}
		
		return jobExperiecePage;
	}
	
	@GetMapping("/{id}")
	public JobExperience show(@PathVariable Integer id) {
		return jeRepo.findById(id).orElseThrow(null);
	}
	
	@PatchMapping("/{id}")
	public JobExperience update(@PathVariable Integer id, @RequestBody JobExperience jobExperience) {
		Optional<JobExperience> jeOptional = jeRepo.findById(id);
		JobExperience jobExp=null;
		if(jeOptional.isPresent()) {
			jobExp=jeOptional.get();
			jobExp.setId(jobExperience.getId());
			jobExp.setCompanyName(jobExperience.getCompanyName());
			jobExp.setPosition(jobExperience.getPosition());
			jobExp.setEmployee(jobExperience.getEmployee());
			jobExp.setStartYear(jobExperience.getStartYear());
			jobExp.setEndYear(jobExperience.getEndYear());
			return jeRepo.save(jobExp);
		}
		return jobExp;
	}
}
