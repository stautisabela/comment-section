package com.stautisabela.commentsection.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stautisabela.commentsection.domain.Post;
import com.stautisabela.commentsection.domain.User;
import com.stautisabela.commentsection.dto.UserDTO;
import com.stautisabela.commentsection.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDto) {
		User user = userDto.fromDTO();
		user = service.insert(user);
		URI createdUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(createdUserUri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO userDto) {
		User user = userDto.fromDTO();
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}
}
