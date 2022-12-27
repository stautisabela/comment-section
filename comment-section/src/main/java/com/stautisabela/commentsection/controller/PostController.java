package com.stautisabela.commentsection.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stautisabela.commentsection.controller.utils.URL;
import com.stautisabela.commentsection.domain.Post;
import com.stautisabela.commentsection.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostController {
	
	@Autowired
	private PostService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value="/{titlesearch}")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String title){
		title = URL.decodeParameter(title);
		List<Post> posts = service.findByTitle(title);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value="/{fullsearch}")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value="text", defaultValue="") String text, 
												 @RequestParam(value="minDate", defaultValue="") String minDate,
												 @RequestParam(value="maxDate", defaultValue="") String maxDate){
		text = URL.decodeParameter(text);
		Date min = URL.convertDate(minDate, new Date (0L));
		Date max = URL.convertDate(maxDate, new Date ());
		List<Post> posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
}
