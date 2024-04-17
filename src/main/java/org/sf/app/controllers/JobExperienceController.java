package org.sf.app.controllers;

import org.hibernate.Hibernate;
import org.sf.app.entities.JobExperience;
import org.sf.app.repositories.JobExperienceRepository;
import org.sf.app.services.JobExperienceService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobExperienceController {
	
	@Autowired
	JobExperienceService jeSrv;
	
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
			jobExperiecePage = jeSrv.findAll(pageable);
		} else {
			jobExperiecePage = jeSrv.findByKeyword(keyword,pageable);
		}
		
		return jobExperiecePage;
	}
}
