package com.stautisabela.commentsection.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stautisabela.commentsection.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
