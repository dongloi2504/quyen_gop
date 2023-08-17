package com.example.quyengopbackend.post.service;


import com.example.quyengopbackend.post.dto.PostDto;
import com.example.quyengopbackend.post.exception.PostNotFoundException;
import com.example.quyengopbackend.post.model.Post;
import com.example.quyengopbackend.post.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
    public Post createPost(PostDto postDto) throws PostNotFoundException {
        Post post = new Post(
                postDto.getName(),
                postDto.getImgUrl(),
                postDto.getContact()
        );
        return postRepository.save(post);
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
