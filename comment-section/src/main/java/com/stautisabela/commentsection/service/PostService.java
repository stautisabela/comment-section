package com.stautisabela.commentsection.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stautisabela.commentsection.domain.Post;
import com.stautisabela.commentsection.repository.PostRepository;
import com.stautisabela.commentsection.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post not found."));
	}
	
	public List<Post> findByTitle(String title) {
		return repository.searchTitle(title);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 *60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	};
}
