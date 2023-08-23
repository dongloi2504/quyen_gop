package com.example.quyengopbackend.post.controller;

import com.example.quyengopbackend.post.dto.PostDto;
import com.example.quyengopbackend.post.exception.PostNotFoundException;
import com.example.quyengopbackend.post.model.Post;
import com.example.quyengopbackend.post.service.PostService;
import com.example.quyengopbackend.security.exception.UserNotFoundException;
import com.example.quyengopbackend.security.model.User;
import com.example.quyengopbackend.security.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private Logger logger = LoggerFactory.getLogger(PostController.class);
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> all() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable String id) {
        try{
            Post post = postService.getOne(id);
            return ResponseEntity.ok(post);
        }catch(PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }

    }

    @PostMapping("")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto) {
        // Save the new post
        try{
            Post createdPost = postService.createPost(postDto);
            return ResponseEntity.ok(createdPost);
        }catch (PostNotFoundException | UserNotFoundException e){
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Post Not Found Error or User Not Found Error");
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable String id){
        postService.deletePost(id);
        return ResponseEntity.ok("Plan deleted successfully");
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchOne(@PathVariable String id,@RequestBody PostDto postDto){
        Post updatedPost;
        try{
            updatedPost = postService.patchPost(id,postDto);
        }catch (PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
        return ResponseEntity.ok(updatedPost);
    }
}


