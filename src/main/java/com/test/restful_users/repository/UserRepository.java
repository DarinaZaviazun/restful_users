package com.test.restful_users.repository;

import com.test.restful_users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findById(String id);

    void deleteById(String id);
}
