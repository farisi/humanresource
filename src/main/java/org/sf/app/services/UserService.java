package org.sf.app.services;

import org.sf.app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	User save(User user);
	Page<User> all(Pageable pageable);
	User findUserById(Integer id);
	void remove(User user);
}
