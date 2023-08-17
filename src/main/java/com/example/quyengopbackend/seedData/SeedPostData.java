package com.example.quyengopbackend.seedData;

import com.example.quyengopbackend.post.controller.PostController;
import com.example.quyengopbackend.post.dto.PostDto;
import com.example.quyengopbackend.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedPostData implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("preloaded: " + postService.createPost(new PostDto("Sach Giao Khoa", "https://images.unsplash.com/photo-1516979187457-637abb4f9353?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80", "nkalk192002@gmail.com")));
        logger.info("preloaded: " + postService.createPost(new PostDto("Quan Ao Cu", "https://images.unsplash.com/photo-1484502249930-e1da807099a5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1548&q=80", "0379345345")));
    }
}
