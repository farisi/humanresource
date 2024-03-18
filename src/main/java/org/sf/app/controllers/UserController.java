package org.sf.app.controllers;

import java.util.List;

import org.sf.app.entities.User;
import org.sf.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping()
	public List<User> index(){
		return userRepo.findAll();
	}
}
