package org.sf.app.controllers;

import java.util.Optional;

import org.sf.app.DTO.UserDetailDTO;
import org.sf.app.entities.UserDetail;
import org.sf.app.requesters.users.UserStore;
import org.sf.app.services.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserDetailService usrDtlSrv;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping()
	public Page<UserDetailDTO> index(
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int pagenum,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "firstName,asc") String[] sort){
		String sortField = sort[0];
		String sortDirection = sort[1];
		
		if(pagenum==0) {
			System.out.println(" pagenum di set " + pagenum);
			pagenum=1;
		}
			
		Direction direction;
		if (sortDirection.equals("desc")) {
			direction = Sort.Direction.DESC;
		} else {
			direction = Sort.Direction.ASC;
		}
		Order order = new Order(direction, sortField);

		Pageable pageable = PageRequest.of(pagenum - 1, pagesize, Sort.by(order));

		Page<UserDetailDTO> pageUser;
		

		if (keyword == null) {
			pageUser = usrDtlSrv.all(pageable);
		} else {
			pageUser = usrDtlSrv.findByKeyword(keyword,pageable);
		}
		
		return pageUser;
	}
	
	@PostMapping()
	public UserDetail store(@Valid  @RequestBody UserStore userStore) {		
		return usrDtlSrv.save(userStore);
	}
	
	@GetMapping("/{id}")
	public UserDetail show(@PathVariable Integer id) {
		Optional<UserDetail> udOpt = usrDtlSrv.findById(id);
		return udOpt.orElseThrow(()-> new ResourceNotFoundException() ); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Integer id) {
		return ResponseEntity.noContent().build();
	}
}