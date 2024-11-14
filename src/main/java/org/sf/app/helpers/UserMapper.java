package org.sf.app.helpers;

import org.sf.app.DTO.UserDTO;
import org.sf.app.entities.User;

public class UserMapper {
	public static UserDTO mapperToDTO(User user) {
		return new UserDTO(user.getId(), user.getEmail(), user.isEnabled(),user.getCreatedAt(),user.getUpdatedAt());
	}
	public static User mapperFromDTO(UserDTO usr) {
		return new User(usr.id(), usr.email(),usr.isEnabled(), usr.createdAt(), usr.updatedAt());
	}
}
