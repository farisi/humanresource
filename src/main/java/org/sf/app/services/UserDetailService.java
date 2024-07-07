package org.sf.app.services;

import java.util.Optional;

import org.sf.app.entities.UserDetail;
import org.sf.app.requesters.users.UserStore;
import org.sf.app.requesters.users.UserUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {
	UserDetail save(UserStore usrStr);
	UserDetail update(UserUpdating usr);
	Page<UserDetail> all(Pageable pageable);
	Optional<UserDetail> findById(Integer id);
	void delete(UserDetail usrDetail);
	Page<UserDetail> findByKeyword(String keyword, Pageable pageable);
}
