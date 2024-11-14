package org.sf.app.repositories;

import org.sf.app.DTO.UserDetailDTO;
import org.sf.app.entities.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDetailRepository extends PagingAndSortingRepository<UserDetail, Integer>,ListCrudRepository<UserDetail, Integer>{

	@EntityGraph(attributePaths = "user")
	@Query("SELECT new org.sf.app.DTO.UserDetailDTO(ud.id, ud.firstName,ud.lastName, ud.foto,ud.placeOfBirth,ud.address,ud.mobile,"
			+ "new org.sf.app.DTO.UserDTO(ud.user.id,ud.user.email,user.isEnabled,user.createdAt,user.updatedAt),ud.birthDate,ud.joinDate,"
			+ "ud.createdAt,ud.updatedAt) FROM UserDetail ud left join  ud.user u " 
			+ "WHERE u.email like concat(:keyword,'%') or ud.firstName like concat(:keyword,'%') or ud.lastName like concat(:keyword, '%')")
	Page<UserDetailDTO> findByKeyword(String keyword, Pageable pageable);
	
	
	@EntityGraph(attributePaths="user")
	@Query("SELECT new org.sf.app.DTO.UserDetailDTO(ud.id, ud.firstName,ud.lastName, ud.foto,ud.placeOfBirth,ud.address,ud.mobile,"
			+ "new org.sf.app.DTO.UserDTO(ud.user.id,ud.user.email,user.isEnabled,user.createdAt,user.updatedAt),ud.birthDate,ud.joinDate,ud.createdAt,ud.updatedAt)  "
			+ "FROM UserDetail ud join  ud.user")
	Page<UserDetailDTO> all(Pageable pageable);

}