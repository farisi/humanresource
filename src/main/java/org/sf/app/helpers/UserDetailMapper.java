package org.sf.app.helpers;

import org.sf.app.DTO.UserDTO;
import org.sf.app.DTO.UserDetailDTO;
import org.sf.app.entities.User;
import org.sf.app.entities.UserDetail;

public class UserDetailMapper {
	public static UserDetailDTO convertToDTO(UserDetail ud) {
		UserDTO usr = UserMapper.mapperToDTO(ud.getUser());
		return new UserDetailDTO(ud.getId(), ud.getFirstName(), ud.getLastName(), ud.getFoto(), ud.getPlaceOfBirth(), ud.getAddress(),ud.getMobile() ,
				usr, ud.getBirthDate(), ud.getJoinDate(), ud.getCreatedAt(), ud.getUpdatedAt());
	}
	
	public static UserDetail convertFromDTO(UserDetailDTO ud) {
		User usr = UserMapper.mapperFromDTO(ud.user());
		return new UserDetail(ud.id(),ud.firstName(),ud.lastName(),ud.foto(),ud.placeOfBirth(),ud.address(),
				ud.mobile(),usr,ud.birthDate(),ud.joinDate(),ud.createdAt(),ud.updatedAt());
	}
}
