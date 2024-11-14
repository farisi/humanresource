package org.sf.app.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.sf.app.entities.User;

public record UserDetailDTO (Integer id, String firstName, String lastName, String foto, String placeOfBirth, String address,
			String mobile, UserDTO user, LocalDate birthDate, LocalDate joinDate, LocalDateTime createdAt,
			LocalDateTime updatedAt){

}
