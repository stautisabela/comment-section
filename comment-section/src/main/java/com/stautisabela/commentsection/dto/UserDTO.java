package com.stautisabela.commentsection.dto;

import java.io.Serializable;

import com.stautisabela.commentsection.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {
	}

	// method to instantiate DTO from User object
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
	
	// method to instantiate User object from DTO
	public User fromDTO() {
		return new User(this.id, this.name, this.email);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
