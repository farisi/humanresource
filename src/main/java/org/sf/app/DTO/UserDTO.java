package org.sf.app.DTO;

import java.time.LocalDateTime;

public record UserDTO(Integer id, String email,  boolean isEnabled,
		LocalDateTime createdAt, LocalDateTime updatedAt) {}
