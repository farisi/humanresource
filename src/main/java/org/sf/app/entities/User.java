package org.sf.app.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name="users")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column(nullable=false)
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@Transient
	private String password_confirm;
	
	@Column(name="is_enabled")
	private boolean isEnabled=true;
		
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	public User() {}

	public User(Integer id, String email, String password, String password_confirm, boolean isEnabled,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.password_confirm = password_confirm;
		this.isEnabled = isEnabled;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public User(Integer id, String email, boolean isEnabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.isEnabled = isEnabled;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_confirm() {
		return password_confirm;
	}

	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
