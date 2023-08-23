package com.example.quyengopbackend.post.service;


import com.example.quyengopbackend.post.dto.PostDto;
import com.example.quyengopbackend.post.exception.PostNotFoundException;
import com.example.quyengopbackend.post.model.Post;
import com.example.quyengopbackend.post.repository.PostRepository;
import com.example.quyengopbackend.security.exception.UserNotFoundException;
import com.example.quyengopbackend.security.model.User;
import com.example.quyengopbackend.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getOne(String id) throws PostNotFoundException {
        Post post = postRepository.findPostById(id).orElseThrow(()->new PostNotFoundException("not found post" + "id" + id));
        return post;
    }

    @Override
    public Post createPost(PostDto postDto) throws PostNotFoundException, UserNotFoundException {

        Optional<User> userOptional = userRepository.findUserByUsername(postDto.getUserName());
        if (!userOptional.isPresent()) {
            // Handle case where the userOptional doesn't exist
            throw new UserNotFoundException("Not Found User");
        }

        // Create a new Post object with userOptional information
        Post newPost = new Post(postDto.getName(), postDto.getImgUrl(), postDto.getContact(), userOptional.get().getId());
        return postRepository.save(newPost);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post patchPost(String id,PostDto postDto){
        Post existingPost = postRepository.findPostById(id)
                .orElseThrow(()->new PostNotFoundException("plan"+"id"+id));
        updatePostProperties(existingPost, postDto);
        Post updatedPost = postRepository.save(existingPost);
        return updatedPost;
    }

    private void updatePostProperties(Post existingPlan, PostDto postDTO){
        if(postDTO.getName()!= null && !postDTO.getName().isEmpty()){
            existingPlan.setName(postDTO.getName());
        }
        if(postDTO.getImgUrl()!= null && !postDTO.getImgUrl().isEmpty() ){
            existingPlan.setImgUrl(postDTO.getImgUrl());
        }
        if(postDTO.getContact()!= null && !postDTO.getContact().isEmpty() ){
            existingPlan.setContact(postDTO.getContact());
        }
    }
}
