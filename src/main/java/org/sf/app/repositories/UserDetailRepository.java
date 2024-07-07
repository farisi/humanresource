package org.sf.app.repositories;

import org.sf.app.entities.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDetailRepository extends PagingAndSortingRepository<UserDetail, Integer>,ListCrudRepository<UserDetail, Integer>{

	@EntityGraph(attributePaths = "user")
	@Query("SELECT ud FROM UserDetail ud left join fetch ud.user u WHERE u.email like concat(:keyword,'%') or ud.firstName like concat(:keyword,'%') or ud.lastName like concat(:keyword, '%')")
	Page<UserDetail> findByKeyword(String keyword, Pageable pageable);
	

	@Query("SELECT ud FROM UserDetail ud join fetch ud.user")
	Page<UserDetail> all(Pageable pageable);

}