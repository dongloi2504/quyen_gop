package com.example.quyengopbackend.post.service;

import com.example.quyengopbackend.post.dto.PostDto;
import com.example.quyengopbackend.post.model.Post;

import java.util.List;

public interface PostService {
    public List<Post> getAll();
    public Post getOne(String id);
    public Post createPost(PostDto postDto);
    public void deletePost(String id);
    public Post patchPost(String id, PostDto partialUpdatePlanDto);
}
