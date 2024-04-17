package org.sf.app.services;

import org.sf.app.repositories.JobExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sf.app.entities.JobExperience;

@Service
public class JobExperienceService {
	
	@Autowired
	JobExperienceRepository jeRepo;
	
	@Transactional(readOnly = true)
	public Page<JobExperience> findAll(Pageable page){
		return jeRepo.findAll(page);
	}

	@Transactional(readOnly = true)
	public Page<JobExperience> findByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return jeRepo.findByKeyword(keyword, pageable);
	}
}
