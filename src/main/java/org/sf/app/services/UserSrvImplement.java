package org.sf.app.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.sf.app.entities.User;
import org.sf.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSrvImplement implements UserService{
	
	@Autowired
	UserRepository userRepo;

	@Transactional
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		User u  = new User();
		if(user.getId()!=null) {		
			u.setId(user.getId());
			u.setCreatedAt(LocalDateTime.now());
		}
		else {
			u.setCreatedAt(LocalDateTime.now());
			u.setUpdatedAt(LocalDateTime.now());
		}
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());	
		
		return userRepo.save(u);
	}

	@Override
	public Page<User> all(Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepo.findAll(pageable);
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isPresent()) {
			return userOpt.get();
		}
		return null;
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
		userRepo.delete(user);
	}
}
