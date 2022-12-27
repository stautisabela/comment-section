package com.stautisabela.commentsection.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stautisabela.commentsection.domain.User;
import com.stautisabela.commentsection.repository.UserRepository;
import com.stautisabela.commentsection.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found."));
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public User update(User alteredUser) {
		User existingUser = findById(alteredUser.getId());
		existingUser.setName(alteredUser.getName());
		existingUser.setEmail(alteredUser.getEmail());
		return repository.save(existingUser);
	}

	public void delete(String id) {
		findById(id); //confirming if ID exists
		repository.deleteById(id);
	}
}
