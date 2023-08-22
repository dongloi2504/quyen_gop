package com.example.quyengopbackend.post.repository;

import com.example.quyengopbackend.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  PostRepository extends MongoRepository<Post, String> {
    Optional<Post> findPostById(String id);

}
