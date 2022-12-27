package com.stautisabela.commentsection.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.stautisabela.commentsection.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	// Case insensitive query for a given post title
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String title);
	
	// Case insensitive query for any text within a date range
	@Query("{ $and: [ { date: { $gte: ?1 }, { date: $lte: ?2 } , { $or : [ { 'title': { $regex: ?0, $options: 'i' } } , { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
