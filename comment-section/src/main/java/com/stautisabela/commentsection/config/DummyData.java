package com.stautisabela.commentsection.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.stautisabela.commentsection.domain.Post;
import com.stautisabela.commentsection.domain.User;
import com.stautisabela.commentsection.dto.AuthorDTO;
import com.stautisabela.commentsection.dto.CommentDTO;
import com.stautisabela.commentsection.repository.PostRepository;
import com.stautisabela.commentsection.repository.UserRepository;

@Configuration
public class DummyData implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		User user1 = new User(null, "Lewis Neill", "lewis@gmail.com");
		User user2 = new User(null, "Mary Ann", "mary@gmail.com");
		User user3 = new User(null, "John Smith", "john@gmail.com");
		User user4 = new User(null, "Jane Smith", "jane@gmail.com");
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
		
		postRepository.deleteAll();
		Post post1 = new Post(null, new AuthorDTO(user1), sdf.parse("10/07/2020"), "Blah blah", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas pulvinar mauris orci, sit amet porttitor arcu sollicitudin aliquam.");
		Post post2 = new Post(null, new AuthorDTO(user2), sdf.parse("10/07/2020"), "Blah blah", "Nullam id magna a felis tempor dapibus. Ut ac libero est. Integer tincidunt nibh eget sagittis lacinia. Morbi bibendum, libero in ultricies faucibus, metus nisi efficitur felis, sit amet congue lectus mi a mauris. Nunc ac dui hendrerit, tincidunt eros sit amet, feugiat eros.");
		Post post3 = new Post(null, new AuthorDTO(user2), sdf.parse("10/07/2020"), "Blah blah", "Maecenas et luctus leo. Suspendisse vel arcu egestas, viverra purus et, hendrerit metus.");
		Post post4 = new Post(null, new AuthorDTO(user3), sdf.parse("10/07/2020"), "Blah blah", "Nunc pellentesque risus at rutrum ornare.");
		Post post5 = new Post(null, new AuthorDTO(user4), sdf.parse("10/07/2020"), "Blah blah", "Nunc pellentesque eleifend porta. Aliquam erat volutpat. Nullam vel leo ante. Sed dapibus ante nunc, sit amet sagittis est auctor ac.");
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
		
		CommentDTO com1 = new CommentDTO("text", sdf.parse("20/07/2020"), new AuthorDTO(user3));
		
		post1.getComments().addAll(Arrays.asList(com1));
		user1.getPosts().add(post1);
		userRepository.save(user1);
		user2.getPosts().addAll(Arrays.asList(post2, post3));
		userRepository.save(user2);
	}
}
