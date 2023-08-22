package com.example.quyengopbackend.security.repository;

import com.example.quyengopbackend.post.model.Post;
import com.example.quyengopbackend.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
