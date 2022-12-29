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
		Post post1 = new Post(null, new AuthorDTO(user1), sdf.parse("08/07/2020"), "First post", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas pulvinar mauris orci, sit amet porttitor arcu sollicitudin aliquam.");
		Post post2 = new Post(null, new AuthorDTO(user2), sdf.parse("10/07/2020"), "Second post", "Nullam id magna a felis tempor dapibus. Ut ac libero est. Integer tincidunt nibh eget sagittis lacinia. Morbi bibendum, libero in ultricies faucibus, metus nisi efficitur felis, sit amet congue lectus mi a mauris. Nunc ac dui hendrerit, tincidunt eros sit amet, feugiat eros.");
		CommentDTO com1 = new CommentDTO("Maecenas et luctus leo.", sdf.parse("09/07/2020"), new AuthorDTO(user3));
		CommentDTO com2 = new CommentDTO("Nunc pellentesque risus at rutrum ornare.", sdf.parse("09/07/2020"), new AuthorDTO(user4));
		CommentDTO com3 = new CommentDTO("Aliquam erat volutpat. Nullam vel leo ante.", sdf.parse("10/07/2020"), new AuthorDTO(user3));
		CommentDTO com4 = new CommentDTO("Nunc pellentesque eleifend porta.", sdf.parse("11/07/2020"), new AuthorDTO(user1));
		
		post1.getComments().addAll(Arrays.asList(com1, com2));
		post2.getComments().addAll(Arrays.asList(com3, com4));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		user1.getPosts().add(post1);
		user2.getPosts().add(post2);
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
}
